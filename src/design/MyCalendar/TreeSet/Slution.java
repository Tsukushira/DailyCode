package design.MyCalendar.TreeSet;

public class Slution {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();

        System.out.println(myCalendar.book(1, 2));
        System.out.println(myCalendar.book(2, 3));
        System.out.println(myCalendar.book(2, 4));
        System.out.println(myCalendar.book(4, 6));
        System.out.println(myCalendar.book(5, 6));

    }
}
