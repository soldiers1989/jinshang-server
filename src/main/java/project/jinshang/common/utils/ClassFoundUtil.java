package project.jinshang.common.utils;

import project.jinshang.common.invoke.MessageHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class ClassFoundUtil {
    private Class clazz;
    private String packagePath;

    public ClassFoundUtil(Class clazz, String packagePath) {
        this.clazz = clazz;
        this.packagePath = packagePath;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String packagePath1="project.jinshang.common.bean";
        String packagePath2="project.jinshang.common.invoke";
        List<Class> handlers=new ClassFoundUtil(MessageHandler.class,packagePath2).getAllClassByPackage();
        for (Class<MessageHandler> clazz2:handlers){
            clazz2.newInstance().dealMessage();
        }
    }

    public List<Class> getAllClassByPackage(){
        ArrayList<Class> resultList=new ArrayList<>();
        try {
            List<Class> getListClass=getClasses(packagePath);
            for (Class clazz1:
                 getListClass) {
                    if(clazz.isAssignableFrom(clazz1)){
                        if (!clazz.equals(clazz1)){
                            resultList.add(clazz1);
                        }
                    }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    private List<Class> getClasses(String packageName) throws ClassNotFoundException,IOException{
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        String path=packageName.replace(".","/");
        Enumeration<URL> resources=classLoader.getResources(path);
        List<File> fileList=new ArrayList<>();
        while(resources.hasMoreElements()){
            URL resource=resources.nextElement();
            fileList.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes=new ArrayList<Class>();
        for (File directory:
             fileList) {
            classes.addAll(findClass(directory,packageName));
        }
        return classes;
    }


    private List<Class> findClass(File directory,String packageName) throws ClassNotFoundException{
        List<Class> classes=new ArrayList<Class>();
        if(!directory.exists()) {
            return classes;
        }
        File[] files=directory.listFiles();
        List<File> fileList=Arrays.asList(files);
        for (File file:
        fileList) {
            if (file.isDirectory()){
                classes.addAll(findClass(file,packageName+"."+file.getName()));
            }else if (file.getName().endsWith(".class")){
                    classes.add((Class)Class.forName(packageName+"."+file.getName().substring(0,file.getName().length()-6)));
            }
        }
        return classes;
    }
}
