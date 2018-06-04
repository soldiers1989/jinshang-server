package project.jinshang.common.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import mizuki.project.core.restserver.util.StringUtil;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NlpUtils {

    private static List<String> ignoreNaturesHanLP;
    private static String[] ignoreNaturesHanLPArr = new String[]{
            "ul","uj","w","d","dg","dl","c","cc","p","pba","pbei"
    };
    @Autowired
    private SynonymMapper synonymMapper;


    @PostConstruct
    public void init() {
        IndexTokenizer.segment("初始化 init");
        HanLP.extractKeyword("初始化 init",1);
        ignoreNaturesHanLP= Arrays.stream(ignoreNaturesHanLPArr).collect(Collectors.toList());
        // 词典
        List<Synonym> synonyms = synonymMapper.listAll();
        synonyms.forEach(synonym -> synonym.getWords().forEach(CustomDictionary::add));
    }


    /**
     * for 保存索引
     */
    public String seqForPgVector(String[] origin){
        for(int i=0;i<origin.length;i++) {
            if(origin[i]==null) origin[i]="";
        }
        List<String> keys = seq_hanlp(origin);
        // 加上同义词
        List<Synonym> synonyms = synonymMapper.searchForQuery(StringUtil.join(keys," | "));
        if(synonyms.size()>0){
            synonyms.forEach(synonym -> {
                keys.addAll(synonym.getWords());
            });
        }
        return tranlatePGVector(keys);
    }

    public String seqForVectorNoSynonym(String... origin){
        for(int i=0;i<origin.length;i++) {
            if(origin[i]==null) origin[i]="";
        }
        List<String> keys = seq_hanlp(origin);
        return tranlatePGVector(keys);
    }

    /**
     * seq for pgsql tsquery , or关系. for 查询字段
     */
    public String seqForPgQuery(String... origin){
        List<String> keys = seq_hanlp(origin);
        return tranlatePGQuery(keys);
    }

    private  List<String> seq_hanlp(String... origin){
        String strs = combine(origin);
        // indextoken 会有较多冗余
//        List<Term> list = IndexTokenizer.segment(strs);

        //使用自定义词库
        Segment segment = HanLP.newSegment().enableCustomDictionary(true);

        List<Term> list = segment.seg(strs);
        List<String> ret = new ArrayList<>();
        list.forEach(term -> {
            if(!ignoreNaturesHanLP.contains(term.nature.name())) ret.add(term.word);
        });
        return ret;
    }

    private  String combine(String... strs){
        if(strs.length==0) return "";
        if(strs.length==1) return strs[0];
        StringBuilder stringBuilder = new StringBuilder();
        for(String s:strs){
            stringBuilder.append(s).append(" ");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    /**
     * translate for tsvector
     */
    private  String tranlatePGVector(Collection list){
        if(list.size()==0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(c->stringBuilder.append(c).append(" "));
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString().toLowerCase();
    }
    /**
     * translate for tsquery
     */
    private  String tranlatePGQuery(Collection list){
        if(list.size()==0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(c->stringBuilder.append(c).append("|"));
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString().toLowerCase();
    }

    public static void main(String[] args) {
        NlpUtils nlpUtils = new NlpUtils();
        ignoreNaturesHanLP= Arrays.stream(ignoreNaturesHanLPArr).collect(Collectors.toList());
        String[] str = new String[]{
                "一二三四五六 我的说的聚合军或无 GB2312"
        };
        String search_str = "GB70 M2.5*4 M6*1 一二三四五六 我的说的聚合军或无 GB2312";
        CustomDictionary.add("GB70");
        CustomDictionary.add("M6*1");
        List<String> keys = nlpUtils.seq_hanlp(search_str,"");
//        System.out.println(HanLP.segment(search_str));
        System.out.println(keys);

    }
}
