package GamePackage;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Mapz {
    private int currentMap;
    private boolean isMapLoaded;
    public Door[][] doorzz;
    public int[][][] dataz;
    public Mapz()
    {
        isMapLoaded = false;
    }
    public int getCurrentMap() {
        return currentMap;
    }

    public void setMapLoaded(boolean mapLoaded) {
        isMapLoaded = mapLoaded;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public Mapz(int currentLevel) throws FileNotFoundException {
        isMapLoaded = true;

        ClassLoader classLoader;
        InputStream inputStreamMap1;
        InputStream inputStreamDoors1;
        Scanner scanner;
        Scanner scanner2;

        switch(currentLevel) {
            case 1:
                dataz = new int[6][][];
                doorzz = new Door[6][];


                classLoader = Mapz.class.getClassLoader();
                inputStreamMap1 = classLoader.getResourceAsStream("level1maps.txt");
                inputStreamDoors1 = classLoader.getResourceAsStream("level1doors.txt");


                scanner = new Scanner(inputStreamMap1);
                scanner2 = new Scanner(inputStreamDoors1);
                for (int k = 0; k < 6; k++)
                {
                        int rows = scanner.nextInt();
                        int cols = scanner.nextInt();
                        dataz[k] = new int[rows][cols];

                        int[][] matrix = new int[rows][cols];

                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) {
                                dataz[k][i][j] = scanner.nextInt();
                            }
                        }

                        int doorAmount = scanner2.nextInt();
                        doorzz[k] = new Door[doorAmount];
                        for(int i = 0 ; i < doorAmount ; i++)
                            doorzz[k][i] = new Door(scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt());
                    }

                scanner.close();
                scanner2.close();
                break;
            case 2:
                dataz = new int[9][][];
                doorzz = new Door[9][];


                classLoader = Mapz.class.getClassLoader();
                inputStreamMap1 = classLoader.getResourceAsStream("level2maps.txt");
                inputStreamDoors1 = classLoader.getResourceAsStream("level2doors.txt");


                scanner = new Scanner(inputStreamMap1);
                scanner2 = new Scanner(inputStreamDoors1);
                for (int k = 0; k < 8; k++)
                {
                    int rows = scanner.nextInt();
                    int cols = scanner.nextInt();
                    dataz[k] = new int[rows][cols];

                    int[][] matrix = new int[rows][cols];

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            dataz[k][i][j] = scanner.nextInt();
                        }
                    }

                    int doorAmount = scanner2.nextInt();
                    doorzz[k] = new Door[doorAmount];
                    for(int i = 0 ; i < doorAmount ; i++)
                        doorzz[k][i] = new Door(scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt());
                }

                scanner.close();
                scanner2.close();
                break;
            case 3:
                dataz = new int[1][][];
                doorzz = new Door[1][];


                classLoader = Mapz.class.getClassLoader();
                inputStreamMap1 = classLoader.getResourceAsStream("level3maps.txt");
                inputStreamDoors1 = classLoader.getResourceAsStream("level3doors.txt");


                scanner = new Scanner(inputStreamMap1);
                scanner2 = new Scanner(inputStreamDoors1);
                for (int k = 0; k < 1; k++)
                {
                    int rows = scanner.nextInt();
                    int cols = scanner.nextInt();
                    dataz[k] = new int[rows][cols];

                    int[][] matrix = new int[rows][cols];

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            dataz[k][i][j] = scanner.nextInt();
                        }
                    }

                    int doorAmount = scanner2.nextInt();
                    doorzz[k] = new Door[doorAmount];
                    for(int i = 0 ; i < doorAmount ; i++)
                        doorzz[k][i] = new Door(scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt(), scanner2.nextInt());
                }

                scanner.close();
                scanner2.close();
                break;
                  }



        }


    public boolean getMapState()
    {
        return isMapLoaded;
    }
    public void printMap()
    {
        for(int k = 0 ; k < dataz.length ; k++)
        {
            for(int i = 0 ; i < dataz[k].length; i++)
            {
            for(int j = 0 ; j < dataz[k][i].length ; j++)
                System.out.print(dataz[k][i][j] + " ");
            System.out.print('\n');
           }
            System.out.print('\n');
        }
    }
}
