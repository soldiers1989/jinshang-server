package DesignPattern;

/**
 * 建造者模式
 *
 * @author xiazy
 * @create 2018-05-23 9:48
 **/
public class Country {
        public String name;
        public String area;
        public double population;
        public static class Builder{
                private String name;
                private String area;
                private double population;

                public String getName() {
                        return name;
                }

                public Builder setName(String name){
                        this.name=name;
                        return this;
                }


                public String getArea() {
                        return area;
                }

                public Builder setArea(String area) {
                        this.area = area;
                        return this;
                }

                public double getPopulation() {
                        return population;
                }

                public Builder setPopulation(double population) {
                        this.population = population;
                        return this;
                }

                public Country build(){
                        return new Country(this);
                }

        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getArea() {
                return area;
        }

        public void setArea(String area) {
                this.area = area;
        }

        public double getPopulation() {
                return population;
        }

        public void setPopulation(double population) {
                this.population = population;
        }

        public Country(Builder builder) {
                this.name = builder.getName();
                this.area = builder.getArea();
                this.population = builder.getPopulation();
        }
}
