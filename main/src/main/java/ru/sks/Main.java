package ru.sks;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication(scanBasePackages = "ru.sks")
public class Main {

    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
        Manager managerImpl = new ManagerImpl();

        Scanner sc = new Scanner(System.in);


        System.out.println("Сервис сокращения ссылок\n");

        while (!Arrays.asList(new Integer[]{1, 2})
                .contains(sc.hasNextInt() ? mode = sc.nextInt() : sc.next())) {
            System.out.println("Введите 1 или 2");
        }

        switch (mode) {
            case 1:
                modeOne(sc);
                break;
            case 2:
                modeTwo(sc);
                break;
        }
    }
}
