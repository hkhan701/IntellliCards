package comp3350.intellicards.Application;

//import comp3350.intellicards.Presentation.CLI;

public class Main
{
    private static String dbName="Intellicards";

    public static void main(String[] args)
    {
        System.out.println("All done");
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
        System.out.println("DB path set to " + dbName);
    }

    public static String getDBPathName() {
        return dbName;
    }
}
