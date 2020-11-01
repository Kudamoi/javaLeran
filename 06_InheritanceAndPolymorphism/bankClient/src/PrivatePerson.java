public class PrivatePerson extends Client {

    PrivatePerson(double count) {
        super(count);
    }

    @Override
    public void getInformation() {
        System.out.println("Информация счета для физического лица:");
        System.out.println("Коммисия для снятия и зачисления: 0%");
    }
}
