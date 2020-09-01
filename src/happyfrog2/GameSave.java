/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class GameSave {

    Handler handler;

    public GameSave() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private File file = new File("F:\\KY 4\\prj321\\HappyFrog2\\src\\happyfrog2\\saveGameObject.txt");
    private File file1 = new File("F:\\KY 4\\prj321\\HappyFrog2\\src\\happyfrog2\\saveGameScore.txt");

    public int checkFile() {
        boolean check = file.exists();
        if (check && file.length() != 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public LinkedList<GameObject> loadGameObjectsd() throws FileNotFoundException, IOException {
        LinkedList<GameObject> objects = new LinkedList<GameObject>();
        int i, count = 0;
        String data = "", id = "", x = "", y = "", height = "";

        Character ch;

        FileReader fileReader = new FileReader(file);
        while ((i = fileReader.read()) != -1) {
            ch = (char) i;

            System.out.println(ch);
            if (ch != 35 && ch != 47) {
                data = data + String.valueOf(ch);
            }
            if (ch == 47 || ch == 35) {
                switch (count) {
                    case 0:
                        id = data;
                        System.out.println("id---" + data);
                        count++;
                        data = "";

                        break;
                    case 1:
                        x = data;
                        System.out.println("x---" + x);
                        count++;
                        data = "";
                        break;
                    case 2:
                        y = data;
                        System.out.println("y---" + y);
                        data = "";
                        count++;
                        break;

                    case 3:
                        if (id.equalsIgnoreCase("Column")) {
                            height = data;
                            System.out.println("height---" + height);
                            data = "";
                        }
                        break;
                }

            }
            if (ch == 35) {
                System.out.println("--------------");
                count = 0;
                if (id.equalsIgnoreCase("Player")) {
                    objects.add(new Player(Double.parseDouble(x), Double.valueOf(y), ID.Player, handler));
                }

                if (id.equalsIgnoreCase("MarkPoint")) {
                    objects.add(new MarkPoint(Double.parseDouble(x), Double.parseDouble(y), ID.MarkPoint));
                }
                if (id.equalsIgnoreCase("Column")) {
                    Integer h=Integer.valueOf(height);
                    int h1=h.intValue();
                    objects.add(new Column(h1, Double.parseDouble(x), Double.parseDouble(y), ID.Column));
                }
            }
        }

        fileReader.close();
        file.delete();

        for (int j = 0; j < objects.size(); j++) {
            GameObject get = objects.get(j);
            System.out.println("ID " + get.getId() + " X " + get.getX() + " Y " + get.getY());
        }
        return objects;

    }

    public void loadScore(HUD hud) throws FileNotFoundException, IOException {
        String data = "";
        Character ch;
        FileReader fileReader = new FileReader(file1);
        int i;
        while ((i = fileReader.read()) != -1) {
            ch = (char) i;
            if (ch != 35) {
                data = data + String.valueOf(ch);
            } else {
                hud.setScore(Integer.valueOf(data));
            }

        }
        fileReader.close();
        file1.delete();
    }

    public void save(Handler handler, String hud) throws FileNotFoundException, IOException {

        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.Column) {
                Column c = (Column) temp;
                String data = String.valueOf(c.getId()) + "/" + String.valueOf(c.getX()) + "/" + String.valueOf(c.getY()) + "/" + String.valueOf(c.getHeight()) + "#";
                System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssss");
                fileWriter.write(data);
            } else {
                String data = String.valueOf(temp.getId()) + "/" + String.valueOf(temp.getX()) + "/" + String.valueOf(temp.getY()) + "#";
                fileWriter.write(data);
            }
        }

        fileWriter.close();
        FileWriter fileWriter1 = new FileWriter(file1);
        String data = hud + "#";
        fileWriter1.write(data);
        fileWriter1.close();
    }

    public void deleteAllFile() throws IOException {
        File file = new File("F:\\KY 4\\prj321\\HappyFrog2\\src\\happyfrog2\\saveGameObject.txt");
        boolean delete = file.delete();
        File file1 = new File("F:\\KY 4\\prj321\\HappyFrog2\\src\\happyfrog2\\saveGameScore.txt");
        boolean delete1 = file1.delete();
    }
}
