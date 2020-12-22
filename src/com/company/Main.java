package com.company;

import java.util.Scanner;

/*
   Написать класс Вектор :
   + C методом printArr()                  печатающий весь вектор
   + C методом removeAt(int count)         удаление по индексу
   + C методом insert(int With,int number) добавление значение по индексу
   + C методом pushEnd(int number)         добавление в конец
   + C методом pushToStart(int number)     добавление в начало
   + C методом popEnd()                    удаление последнего элемента
   + C методом popToStart()                удаление с начала
   + C методом fillArr()                   заполнение вектора
   + C методом sortUp()                    сортировка по возрастанию
   + C методом sortDown()                  сортировка по убыванию
   + C методом capacityUp()                регулировщик размерности
   + С методом shiftL()                    сдвиг влево 1 раз
   + С методом shiftR()                    сдвиг влево 1 раз
   + С методом shiftLN()                    сдвиг влево N раз
   + С методом shiftRN()                    сдвиг влево N раз
   + С конструктором копирование
 */

class Vector {
    private int[] arr;
    private int size;
    private int index = 0;
    private int capacity = 5;

    Vector() {
        size = capacity;
        arr = new int[size];
        fillArr();
    }

    public Vector(Vector vector) {
        this.index = vector.index;
        this.size = vector.size;
        this.capacity = vector.capacity;

        this.arr = new int[vector.size];

        for (int i = 0; i < index; i++) {
           this.arr[i] =  vector.arr[i] ;
        }
    }

    // Вспомогательная функция
    private void shiftL(){  // 1 , 2 , 3 , 4   =>    2 , 3 , 4, 1

        int tmp = arr[0];

        for (int i = 0; i < index-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[index-1] = tmp;
    }

    // Вспомогательная функция
    private void shiftR(){ // 1 , 2 , 3 , 4   =>    4 , 1 , 2 , 3
        int tmp = arr[index-1];

        for (int i =  index-1; i >=1; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }

    void shiftRN(int times){
        while (times>0){
            shiftR();
            times--;
        }
    }

    void shiftLN(int times){
        while (times>0){
            shiftL();
            times--;
        }
    }

    Vector(int size) {
        this.index = size;
        arr = new int[size];
        capacityUp();
        fillArr();
    }

    void printArr() {
        System.out.print("\nPrint : ");
        for (int i = 0; i < index; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    boolean removeAt(int count) {
        if (count<index){
            index--;
            int[] tmp = new int[size];

            for (int i = 0, b = 0; b < index+1; i++) {
                if (i == count) {
                    continue;
                }
                tmp[b] = arr[i];
                b++;
            }
            arr = tmp;
            return true;
        }
        return false;
    }

    boolean insert(int countWith, int number) {
        if (index>countWith){
            index++;
            capacityUp();
            int[] tmp = new int[size ];

            for (int i = 0, b = 0; b < index-1; i++) {

                if (i == countWith) {
                    tmp[i] = number;
                } else {
                    tmp[i] = arr[b];
                    b++;
                }
            }
            arr = tmp;
            return true;
        }
        return false;
    }

    void pushEnd(int number) {
        index++;
        capacityUp();
        int[] tmp = new int[size]; // создаем массив на 1 элемент больше

        for (int i = 0; i < index-1; i++) {
            tmp[i] = arr[i]; // делаем копирование старого массива
        }

        tmp[index-1] = number; // добавлаем в конец новый элемент
        arr = tmp;
    }

    void pushToStart(int number) {

        index++;
        capacityUp();

        int[] tmp = new int[size];

        tmp[0] = number;
        for (int i = 0, b = 1; b < index; i++, b++) {
            tmp[b] = arr[i];
        }
        arr = tmp;
    }

    void popEnd() {
        index--;
        int[] tmp = new int[size];

        for (int i = 0; i < index; i++) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }

    void popToStart() {
        index--;
        int[] tmp = new int[size];

        for (int i = 0, b = 1; i < index; b++, i++) {
            tmp[i] = arr[b];
        }
        arr = tmp;
    }

    // Вспомогательная функция
    private void fillArr() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < index; i++) {
            arr[i] = scanner.nextInt();
        }
    }

    // Вспомогательная функция
    private void capacityUp(){
        while (true){
             if (index<size){
                 break;
             }
             size+=capacity;
        }

        int[] tmp = new int[size];
        for (int i = 0; i < index; i++) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }

    void sortUp(){
        for (int i = 0; i < index; i++) {
            for (int j = i; j < index; j++) {
                if (arr[i]>arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    void sortDown(){
        for (int i = 0; i < index; i++) {
            for (int j = i; j < index; j++) {
                if (arr[i]<arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}

public class Main {

    
    public static void main(String[] args) {


        Vector vector = new Vector(5);


        vector.shiftLN(1);
        System.out.print("\nVector = ");
        vector.printArr();

        Vector arr = new Vector(vector);

        System.out.print("\n\nArr = ");
        arr.printArr();
        vector.shiftLN(1);
        vector.shiftLN(1);

        System.out.print("\n\nArr = ");
        arr.printArr();
        arr.shiftLN(1);
        System.out.print("\n\nVector = ");
        vector.printArr();

        vector.sortDown();
        vector.printArr();

        vector.pushToStart(10);
        vector.printArr();

        vector.pushEnd(999);
        vector.printArr();

        vector.insert(0,-500);
        vector.printArr();

        vector.insert(500,-500);
        vector.printArr();

        vector.removeAt(4);
        vector.printArr();

        vector.pushEnd(999);
        vector.printArr();

        vector.popToStart();
        vector.printArr();

        vector.popEnd();
        vector.printArr();
    }
}
