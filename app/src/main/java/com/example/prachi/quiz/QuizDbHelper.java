package com.example.prachi.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.prachi.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Quiz.db";
    private static final int DATABASE_VERSION=2;


    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public static  synchronized  QuizDbHelper getInstance(Context context){
        if(instance==null){
            instance=new QuizDbHelper((context.getApplicationContext()));
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE= "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("C++");
        addCategory(c1);
        Category c2 = new Category("JAVA");
        addCategory(c2);
        Category c3 = new Category("Python");
        addCategory(c3);
    }
    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionsTable() {
        Question q1 = new Question("What is the full form of OOP?",
                "a) Object oriented programming", "b) Oriented object programming", "c) Office oriented programming", 1,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q1);
        Question q2 = new Question("Which data type is used to represent the absence of parameters?",
                "a) int", "b) short", "c) void", 3,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q2);
        Question q3 = new Question("Object oriented programming employs_________ programming approach. ",
                "a) top-down", "b) procedural", "c) bottom-up", 3,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q3);
        Question q4 = new Question("The value 132.54 can be represented using which data type?",
                "a) double", "b) void", "c) int", 1,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q4);
        Question q5 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, 4);
        addQuestion(q5);
        Question q6 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, 5);
        addQuestion(q6);
        Question q7 = new Question("Constant variables can be created in CPP by using ________ . ",
                "a) const", "b) #define", "c) Both a & b", 3,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q7);
        Question q8 = new Question("Which one is not a correct variable type in C++",
                "a) double", "b) real", "c) long", 2,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q8);
        Question q9 = new Question("Can we overload functions in C++?",
                "a) Yes", "b) No", "c) depend on code", 1,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q9);
        Question q10 = new Question("What is size of void in bytes?",
                "a) 1", "b) 2", "c) 0", 3,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q10);
        Question q11 = new Question("Which of the following is not a type of constructor?",
                "a) Copy constructor", "b) Friend constructor ", "c) Default constructor", 2,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q11);
        Question q12 = new Question("‘cin’ is an __",
                "a) Class", "b) Object", "c) Package", 2,
                Question.DIFFICULTY_EASY, Category.CPP);
        addQuestion(q12);


        Question q13 = new Question("What following operator is called ' ? : '",
                "a) Scope Resolution Operator", "b) Conditional Operator", "c) Ternary Operator", 3,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q13);
        Question q14 = new Question("How to access and edit data in data file handling using structures",
                "a) read()", "b) write()", "c) Ternary Operator", 3,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q14);
        Question q15 = new Question("What is correct syntax of for loop?",
                "a) for(increment/decrement; initialization; condition) ", "b) for(initialization;condition; increment/decrement)", "c) for(initialization, condition, increment/decrement)", 2,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q15);
        Question q16 = new Question("How many object can be created of a Class in C++?",
                "a) 1", "b) 256", "c) There is no limit", 3,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q16);
        Question q17 = new Question("Which of the following cannot be friend?",
                "a) Function", "b) Object", "c) class", 2,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q17);
        Question q18 = new Question("Which of the following concepts of OOPS means exposing only necessary information to client?",
                "a) Data hiding", "b) Abstraction", "c) Encapsulation", 1,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q18);
        Question q19 = new Question("How many types of polymorphisms are supported by C++?",
                "a) 1", "b) 2", "c) 4", 2,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q19);
        Question q20 = new Question("Which of the following is correct about function overloading?",
                "a) The types of arguments are different.", "b) The order of arguments are different.", "c) Both a & b", 3,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q20);
        Question q21 = new Question("Which of the following is the correct class of the object cout?",
                "a) iostream", "b) ostream", "c) istream", 2,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q21);
        Question q22 = new Question("Which of the following problem causes an exception?",
                "a) A run-time error", "b) A syntax error.", "c) A problem in calling function", 1,
                Question.DIFFICULTY_MEDIUM, Category.CPP);
        addQuestion(q22);


        Question q23 = new Question("What will i and j equal after the code below is executed? \n\n int i=5; \n int j=i++;",
                "a) i=6,j=5", "b) i=6,j=6", "c) i=5,j=6", 1,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q23);
        Question q24 = new Question("What can go wrong in resource management on c++?",
                "a)  Exhaustion", "b) Dangling", "c) Both a & b", 3,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q24);
        Question q25 = new Question("When do we call that resource is leaked?",
                "a) Arise of compile time error", "b) Arise of runtime error", "c) It cannot be accessed by any standard mean", 3,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q25);
        Question q26 = new Question("What kind of error can arise when there is a problem with memory?",
                "a) Semaphore error", "b) Segmentation fault", "c) none of this", 2,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q26);
        Question q27 = new Question("What are the operators available in dynamic memory allocation?",
                "a) new", "b) delete", "c) both new and delete", 3,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q27);
        Question q28 = new Question("What is meant by garbage collection??",
                "a) The form of automatic memory management", "b) The form of manual memory management", "c) Used to replace the variables", 1,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q28);
        Question q29 = new Question("Which statement is used to catch all types of exceptions?",
                "a) catch(…)", "b) catch()", "c) catch(Test t)", 1,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q29);
        Question q30 = new Question("Which is used to solve the memory management problem in c++?",
                "a) stack", "b)  smart pointers", "c) arrays", 2,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q30);
        Question q31 = new Question("How to handle error in the destructor?",
                "a) By exception handle", "b) Throwing", "c) terminate", 3,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q31);
        Question q32 = new Question("What kind of exceptions are available in c++?",
                "a)  unhandled", "b)  handled", "c) dynamic", 1,
                Question.DIFFICULTY_HARD, Category.CPP);
        addQuestion(q32);


        Question q33 = new Question("What is the range of short data type in Java?",
                "a) -128 to 127", "b) -32768 to 32767", "c) -2147483648 to 2147483647", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q33);
        Question q34 = new Question("Which data type value is returned by all transcendental math functions?",
                "a) int", "b) float", "c) Double", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q34);
        Question q35 = new Question("A process that involves recognizing and focusing on the important characteristics of a situation or object is known as:",
                "a)  Abstraction ", "b) Encapsulation", "c) inheritance", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q35);
        Question q36 = new Question("Which statement is true regarding an object?",
                "a) An object is what classes instantiated are from", "b) An object is an instance of a class", "c) An object is a variable", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q36);
        Question q37 = new Question("Which of these field declarations are legal within the body of an interface?",
                "a) Private final static int answer = 42", "b) public static int answer=42", "c) final static answer =42", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q37);
        Question q38 = new Question("A package is a collection of",
                "a) Classes and interfaces", "b) Editing tools and interfaces.", "c)  Editing tools", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q38);
        Question q39 = new Question("Basic Java language functions are stored in which of the following java package?",
                "a) java.net", "b) java.io", "c) java.lang", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q39);
        Question q40 = new Question("Which of the following is a member of the java.lang package?",
                "a) List", "b) Queue", "c) Math", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q40);
        Question q41 = new Question("Object-oriented inheritance models the",
                "a) “is a kind of” relationship", "b) “has a” relationship", "c) “want to be” relationship", 1,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q41);
        Question q42 = new Question("In object-oriented programming, new classes can be defined by extending existing classes. This is an example of:",
                "a) Composition", "b) Interface", "c) Inheritance", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q42);


        Question q43 = new Question("Which component is used to compile, debug and execute java program?",
                "a) JVM", "b) JDK", "c) JRE", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q43);
        Question q44 = new Question("Which of the following is a valid declaration of an object of class Box?",
                "a) Box obj = new Box();", "b) Box obj = new Box;", "c) new Box obj;", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q44);
        Question q45 = new Question("Which of these operators is used to allocate memory for an object?",
                "a) malloc", "b) alloc", "c) new", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q45);
        Question q46 = new Question("Which of the following is a method having same name as that of it’s class?",
                "a) finalise", "b) constructor", "c) delete", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q46);
        Question q47 = new Question("Which keyword is used by the method to refer to the object that invoked it?",
                "a) this", "b) catch", "c) super", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q47);
        Question q48 = new Question("Which function is used to perform some action when the object is to be destroyed?",
                "a) delete()", "b) main()", "c) finalize()", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q48);
        Question q49 = new Question("What would be the behaviour if this() and super() used in a method?",
                "a) runtime error", "b) thorws exception", "c) compile time error", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q49);
        Question q50 = new Question("Which of the following has the highest memory requirement?",
                "a) heap", "b) JVM", "c) stack", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q50);
        Question q51 = new Question("Which of the below is not a memory leak solution?",
                "a) process restart", "b) code changes", "c) GC parameter tuning", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q51);
        Question q52 = new Question("Which of these is used to access a member of class before object of that class is created?",
                "a) private", "b) static", "c) protected", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q52);


        Question q53 = new Question("What is the process by which we can control what parts of a program can access the members of a class?",
                "a) Recursion", "b) Abstraction", "c) Encapsulation", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q53);
        Question q54 = new Question("Arrays in Java are implemented as?",
                "a) class", "b) object", "c) variable", 2,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q54);
        Question q55 = new Question("Which of these methods must be made static?",
                "a) main()", "b) delete()", "c) run()", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q55);
        Question q56 = new Question("Which of these methods is used to know whether a given Character object is part of Java’s Identifiers?",
                "a) isIdentifier()", "b) isJavaIdentifier()", "c)  isJavaIdentifierPart()", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q56);
        Question q57 = new Question("Which of these coding techniques is used by method isDefined()?",
                "a) ANSI", "b) UNICODE", "c) ASCII", 2,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q57);
        Question q58 = new Question("Which of these is a super class of Character wrapper?",
                "a) Number", "b) Float", "c) Long", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q58);
        Question q59 = new Question("Which of these class have only one field?",
                "a) boolean", "b) Byte", "c) void", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q59);
        Question q60 = new Question("Which of these methods is used to know whether a string contains “true”?",
                "a) valueOf()", "b) valueOfString()", "c) getString()", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q60);
        Question q61 = new Question("Which of these class contains all the methods present in Math class?",
                "a) Classloader", "b) StrictMath", "c) SystemMath", 2,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q61);
        Question q62 = new Question("toRadian() and toDegree() methods were added by which version of Java?",
                       "a) Java 1.0", "b) Java 1.5", "c) Java 2.0", 3,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q62);



        Question q63 = new Question(" Which one of these is floor division?",
                "a) \\", "b) //", "c) %", 2,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q63);
        Question q64 = new Question("What is the output of this expression, 3*1**3?",
                "a) 27", "b) 9", "c) 3", 3,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q64);
        Question q65 = new Question("Which one of the following have the highest precedence in the expression?",
                "a) Parentheses", "b) Exponential", "c) Multiplication", 1,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q65);
        Question q66 = new Question("Which of these in not a core data type?",
                "a) List", "b) Class", "c) Dictionary", 2,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q66);
        Question q67 = new Question("Which of the following will run without errors ?",
                "a) round(45.8)", "b) round()", "c) round(7463.123,2,1)", 1,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q67);
        Question q68 = new Question("What is the return type of function id ?",
                "a) dict", "b) bool", "c) int", 3,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q68);
        Question q69 = new Question(" What error occurs when you execute?\n\n apple = mango",
                "a) SyntaxError", "b) NameError", "c) TypeError", 2,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q69);
        Question q70 = new Question("What data type is the object below ?\n\n T = [1, 23, ‘hello’, 1].",
                "a) List", "b) Array", "c) Tuple", 1,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q70);
        Question q71 = new Question(" In order to store values in terms of key and value we use what core data type.",
                "a) Class", "b) List", "c) Dictionary", 3,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q71);
        Question q72 = new Question("What is the return value of trunc()?",
                "a) float", "b) int", "c) bool", 2,
                Question.DIFFICULTY_EASY, Category.Python);
        addQuestion(q72);



        Question q73 = new Question(" What is the output of print 0.1 + 0.2 == 0.3?",
                "a) True", "b) False", "c) Error", 2,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q73);
        Question q74 = new Question("What is the type of inf?",
                "a) Float", "b) Complex", "c) Integer", 1,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q74);
        Question q75 = new Question(" What does ~~~~~~5 evaluate to?",
                "a) -5", "b) -11", "c) +5", 3,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q75);
        Question q76 = new Question("What does ~4 evaluate to?",
                "a) -3", "b) -5", "c) -4", 2,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q76);
        Question q77 = new Question("Which of the following is incorrect?",
                "a) 03964", "b) 19023", "c) 0x4f5", 1,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q77);
        Question q78 = new Question("What is the result of round(0.5) – round(-0.5)?",
                "a) 1.0", "b) 0.0", "c) 2.0", 3,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q78);
        Question q79 = new Question("What does 3 ^ 4 evaluate to?",
                "a) 12", "b) 7", "c) 81", 2,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q79);
        Question q80 = new Question("Which of the following operators has its associativity from right to left?",
                "a) **", "b) %", "c) //", 1,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q80);
        Question q81 = new Question(" The output of the expression is:\n\n bin(29)",
                "a) ) ‘0b11111’", "b) ‘0b10111’", "c) ‘0b11101’", 3,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q81);
        Question q82 = new Question(" What is the result of the expression if x=15 and y=12:\n\n x & y",
                "a) 15", "b) 12", "c) b1101", 2,
                Question.DIFFICULTY_MEDIUM, Category.Python);
        addQuestion(q82);



        Question q83 = new Question("Which of the following represents the bitwise XOR operator?",
                "a) |", "b) ^", "c) &", 2,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q83);
        Question q84 = new Question(" What is the value of this expression?\n\n bin(0x8)",
                "a) ‘0b1000’", "b) ‘0bx1000’", "c) 8", 1,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q84);
        Question q85 = new Question("Bitwise _________ gives 1 if either of the bits is 1 and 0 when both of the bits are 1.",
                "a) OR", "b) XNOR", "c) XOR", 3,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q85);
        Question q86 = new Question("Which of the following expressions can be used to multiply a given number ‘a’ by 4?",
                "a) a>>4", "b) a<<2", "c) a>>2", 2,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q86);
        Question q87 = new Question("The output of which of the codes shown below will be: “There are 4 blue birds.”?",
                "a) There are %g %d birds.’ %4 %blue", "b) There are %s %d birds.’ %[4, blue]", "c) There are %d %s birds.’ %(4, blue)", 3,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q87);
        Question q88 = new Question("What is the output of the following?\n\n for i in range(0):\nprint(i)",
                "a) no output", "b) Error", "c) 0", 1,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q88);
        Question q89 = new Question(" What arithmetic operators cannot be used with strings ?",
                "a) +", "b) -", "c) *", 2,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q89);
        Question q90 = new Question("The format function, when applied on a string returns :",
                "a) int,", "b) bool", "c) str", 3,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q90);
        Question q91 = new Question("Given a string example=”hello” what is the output of example.count(l)",
                "a) 2", "b) 1", "c) 0", 1,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q91);
        Question q92 = new Question("To concatenate two strings to a third what statements are applicable ?",
                "a) s3 = s1.__add__(s2)", "b) s3 = s1.add(s2)", "c) s3 = s1.s2", 1,
                Question.DIFFICULTY_HARD, Category.Python);
        addQuestion(q92);

    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }


    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst())
        {
            do{
                Question question=new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }


    public ArrayList<Question> getQuestions(int categoryID, String difficulty){
        ArrayList<Question> questionList = new ArrayList<>();
        db=getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(c.moveToFirst())
        {
            do{
                Question question=new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
