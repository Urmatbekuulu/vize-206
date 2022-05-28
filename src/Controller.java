import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collections;

public class Controller implements ActionListener {
    Model model;
    View view;
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        addListeners();
    }

    private void addListeners(){
        view.drawGraphic.addActionListener(this);
        view.calculateR.addActionListener(this);
        view.readFromFile.addActionListener(this);
        view.writeToFile.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Read from file":
                readFromFile();
                break;
            case "Write to file":
                readFromFile();
                calculateR();
                writeToFile();
                break;
            case "Draw graphic":
                readFromFile();
                calculateR();
                view.g.setBounds(150,10,view.getWidth()-40,view.getHeight()-120);
                view.g.setInit(model);
                view.g.repaint();

                break;
            case "Calculate r":
                calculateR();
                break;
            default:

                break;
        }

    }
    private void calculateR(){

        if(model.vecX.isEmpty() || model.vecY.isEmpty() || model.vecX.size() != model.vecY.size())
        {
            System.out.println("Model data is empty");
            return;
        }

        model.Xa = 0;
        model.Ya = 0;
        for(int i = 0; i<model.vecX.size(); i++){
            model.Xa+= model.vecX.elementAt(i);
            model.Ya+=model.vecY.elementAt(i);
        }
        model.Xa = model.Xa/(float) model.vecX.size();
        model.Ya = model.Ya/(float) model.vecY.size();

        int n = model.vecX.size();

        /* standard deviation formula

         */
        float sumXSquare = 0;
        float sumYSquare = 0;
        float sumX = 0;
        float sumY = 0;
        double sum = 0;
        for (int i = 0; i<n;i++){
            sumXSquare+=Math.pow(model.vecX.elementAt(i) - model.Xa,2);
            sumYSquare+=Math.pow(model.vecY.elementAt(i) - model.Ya,2);
        }
        model.Sx = (float)Math.sqrt(sumXSquare/(n-1));
        model.Sy = (float)Math.sqrt((sumYSquare/(n-1)));

        for(int i = 0;i<n;i++){
            sum+=((model.vecX.elementAt(i)- model.Xa)/ model.Sx)
                    *((model.vecY.elementAt(i)-model.Ya)/ model.Sy);
        }



        model.r =sum/(n-1);

    }

    private void writeToFile() {
        int counter = 0;
        String charset = "UTF-8";

        File file = new File("src/output.txt");
        PrintWriter writer;


        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        writer.println("Xa = "+model.Xa);
        writer.println("Sx = "+model.Sx);
        writer.println("Ya = "+model.Ya);
        writer.println("Sy = "+model.Sy);
        writer.println("r  = "+model.r);
        writer.close();

    }
    private void readFromFile(){
        model.vecX.clear();
        model.vecY.clear();

        Boolean isRead = false;

        FileInputStream fin = null;
        try {

            fin = new FileInputStream("src/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(" ");

                if(attributes.length==2)
                {
                    float tmpX;
                    float tmpY;

                    try {
                        // returns the float value
                        // represented by the string argument
                        tmpX = Float.parseFloat(attributes[0]);
                        tmpY = Float.parseFloat(attributes[1]);
                    }

                    catch (Exception e) {
                        //view.errorField.setText("Exception: " + e);
                        break;
                    }

                    model.vecX.add(tmpX);
                    model.vecY.add(tmpY);
                    //view.errorField.setText("No Problem :) perfect");
                    isRead = true;
                }
                else{
                   // view.errorField.setText("Error with format in text");
                    break;
                }
                line = br.readLine();
            }
            fin.close();
            br.close();
        }
        catch (FileNotFoundException exception) {
            //view.errorField.setText("File Not Found");
        }
        catch (IOException exception) {
           // view.errorField.setText("IOException");
        }

    }
}
