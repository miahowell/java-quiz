package dsfinal;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;

public class DSFinal {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File userInfo = new File("C:\\Users\\Howel\\Downloads\\UsersInfo (1).txt");
        Scanner read = new Scanner(userInfo);
        Scanner keyboard = new Scanner(System.in);
        HashMap <String, String> users = new HashMap<String, String>(); 
        while (read.hasNext()) {
            String fName = read.next();
            String lName = read.next();
            String username = read.next();
            String password = read.next();
            String role = read.next();
            users.put(username, password + " " + fName + " " + lName + " " + role);
            
        }
        read.close();
        int attempt = 0;
        boolean login = false;
        while(true) {
            while (attempt < 3) {
                System.out.println("Username: ");
                String user = keyboard.nextLine();
                if(user.equalsIgnoreCase("done")) {
                    System.out.println("Exiting quiz");
                    keyboard.close();
                    return;
                }
                System.out.println("Password: ");
                String pass = keyboard.nextLine();
                if (users.containsKey(user)) {
                    String str = users.get(user);
                    String[] info = str.split("\\s+");
                    if(info[0].equals(pass)){
                        login = true;
                        System.out.println("Login successful");
                        //System.out.println(info[3]);
                        if (info[3].equals("Instructor")) {
                            instructor();
                            break;
                        }
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                        String formattedDateTime = now.format(formatter);
                        String filename = user + "_COCS_236_Quiz_" + formattedDateTime + ".txt";
                        PrintWriter write = new PrintWriter(filename);
                        write.println("First name: " + info[1]);
                        write.println("Last name: " + info[2]);
                        
                        LocalDateTime startTime = LocalDateTime.now();
                        quiz(write);
                        LocalDateTime stopTime = LocalDateTime.now();
                        Duration duration = Duration.between(startTime, stopTime);
                        long min = duration.toMinutes();
                        long sec = duration.getSeconds() % 60;
                        write.println("Time elapsed: " + min + ":" + sec);
                        write.close();
                        results(filename);
                        break;
                    }
                } else {
                    System.out.println("Login failed, try again");
                    attempt++;
                }    
            }
            if (!login) {
                System.out.println("Out of attempts");
            } 
        }
    }
    
    public static void instructor() throws FileNotFoundException, IOException {
        Scanner read = new Scanner(System.in);
        System.out.println("1. Register student");
        System.out.println("2. Display stats");
        System.out.println("3. Add new questions");
        int choice = read.nextInt();
        if (choice > 3 || choice < 1) {
            System.out.println("Invalid function, try again");
            choice = read.nextInt();
        }
        //add student
  
        if (choice == 1) {
            FileWriter fw = new FileWriter ("C:\\Users\\Howel\\Downloads\\UsersInfo (1).txt", true);
            PrintWriter addStudent = new PrintWriter(fw);
            System.out.println("Please fill out the following information:");
            System.out.println("First name:");
            read.nextLine();
            String fName = read.nextLine();
            System.out.println("Last name:");
            String lName = read.nextLine();
            System.out.println("Assign username:");
            String username = read.nextLine();
            System.out.println("Assing password:");
            String password = read.nextLine();
            String role = "Student";
            String line = fName + "\t" + lName + "\t" + username + "\t" + password + "\t" + role;
           
            addStudent.println();
            addStudent.print(line);
            addStudent.close();
            System.out.println("Student successfully added");
        }
        //display stats
        if (choice == 2) {
            File folder = new File(".");
            File[] files = folder.listFiles((dir, name) -> name.contains("COCS_236_Quiz"));
            int total = 0;
            int highest = Integer.MIN_VALUE;
            int lowest = Integer.MAX_VALUE;
            int count = 0;
            for (File file : files) {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.startsWith("Final Score: ")) {
                        String[] separate = line.split("[/:]");
                        int score = Integer.parseInt(separate[1].trim());
                        total += score;
                        if (score > highest) {
                            highest = score;
                        }
                        if (score < lowest) {
                            lowest = score;
                        }
                        count++;
                    }
                }
                scan.close();
            }
            double average = total / count;
            System.out.println("Highest score: " + highest);
            System.out.println("Lowest score: " + lowest);
            System.out.println("Average score: " + average);
        }
        //add new question
        if (choice == 3) {
            Scanner add = new Scanner(System.in);
            System.out.println("What question do you want to add, true/false questions only");
            String addQ = add.nextLine();
            System.out.println("What is the correct answer?(answer in all caps)");
            String addA = add.nextLine();
            
            PrintWriter addQuestion = new PrintWriter(new FileWriter("C:\\Users\\Howel\\OneDrive\\Documents\\NetBeansProjects\\DSFinal\\TestBank.txt", true));
            
            addQuestion.println(addQ);
            addQuestion.close();
            PrintWriter addAnswer = new PrintWriter(new FileWriter("C:\\Users\\Howel\\OneDrive\\Documents\\NetBeansProjects\\DSFinal\\Answers.txt", true));
            //addAnswer.println();
            addAnswer.println(addA);
            addAnswer.close();
            System.out.println("Added question to quiz");
        }
    }
    
    public static void quiz(PrintWriter write) throws FileNotFoundException{
        File questions = new File("C:\\Users\\Howel\\OneDrive\\Documents\\NetBeansProjects\\DSFinal\\TestBank.txt");
        File answers = new File("C:\\Users\\Howel\\OneDrive\\Documents\\NetBeansProjects\\DSFinal\\Answers.txt");
        
        Scanner ask = new Scanner(questions);
        Scanner check = new Scanner(answers);
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        DLL quest = new DLL();
        DLL ans = new DLL();
        while (ask.hasNext()) {
            String Q = ask.nextLine();
            String A = check.nextLine();
            quest.add(Q);
            ans.add(A);
        }
        ask.close();
        check.close();
        String userAns;
        System.out.println();
        int score = 0;
        HashSet<Integer> index = new HashSet<>();
        DLL QwA = new DLL();
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(quest.size());
            if(!index.contains(x)){
                index.add(x);
                System.out.println("Question " + (i+1) + ": ");
                quest.printAt(x);
            }
            userAns = keyboard.nextLine();

            while (!userAns.equalsIgnoreCase("T") && !userAns.equalsIgnoreCase("F") && !userAns.equalsIgnoreCase("True") && !userAns.equalsIgnoreCase("False")) {
                System.out.println("Invalid answer, try again.");
                quest.printAt(x);
                userAns = keyboard.nextLine();

            }
            String save = "Question " + (i+1) + " | Your answer: " + userAns + ", Correct answer: " + ans.quest(x);
            QwA.add(save);
            if (userAns.equalsIgnoreCase("T")) {
                userAns = ("TRUE");
            }
            if (userAns.equalsIgnoreCase("F")) {
                userAns = ("FALSE");
            }
            if (ans.quest(x).equalsIgnoreCase(userAns)) {
                score++;
            } 
            
        }
        write.println("Quiz results: ");
        for (int i = 0; i < QwA.size(); i++) {
            write.println(QwA.quest(i));
        }
        write.println("Final Score: " + score);
    }
    
    public static void results(String file) throws FileNotFoundException{
        Scanner read = new Scanner(new File(file));
        while (read.hasNextLine()) {
            String line = read.nextLine();
            System.out.println(line);
        }
        read.close();
    }
    
}
