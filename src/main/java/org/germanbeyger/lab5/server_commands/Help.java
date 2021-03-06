package org.germanbeyger.lab5.server_commands;

import java.util.Scanner;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public final class Help {
    private Help() {}

    public static String execute(String[] commandArgs, TargetCollection targetCollection, Scanner stdInScanner) {
        String result = "";
        result += "\nusage: <main-class> filepath";
        result += "filepath: путь к xml-файлу с коллекцией.\n";
        result += "Интерактивный режим: <команда> <аргументы>\n";
        result += "Список команд";
        result += "help : вывести справку по доступным командам\n" +
        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
        "add {element} : добавить новый элемент в коллекцию\n" +
        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
        "remove_by_id id : удалить элемент из коллекции по его id\n" +
        "clear : очистить коллекцию\n" +
        "save : сохранить коллекцию в файл\n" +
        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
        "exit : завершить программу (без сохранения в файл)\n" +
        "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
        "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
        "history : вывести последние 11 команд (без их аргументов)\n" +
        "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
        "print_descending : вывести элементы коллекции в порядке убывания\n" +
        "print_unique_type : вывести уникальные значения поля type всех элементов в коллекции";
        return result;
    }
}
