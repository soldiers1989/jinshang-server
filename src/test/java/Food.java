public interface Food {
    enum Coffee implements Food{
        BLACK_COFFEE("黑咖啡"),DECAF_COFFEE("低咖咖啡"),LATTE("拿铁咖啡"),CAPPUCCINO("卡布奇诺");
        String name;

        Coffee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Coffee{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    enum Dessert implements Food{
        FRUIT,CAKE,GELATO
    }
}
