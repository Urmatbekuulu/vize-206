import java.awt.*;

public class DisplayGraphics extends Canvas{
    Model model;
    Graphics g;

    int scaleX, scaleY;
    int posX, posY;
    String buttonName;
    DisplayGraphics(){

    }
    public void paint(Graphics g){
        this.g = g;
        drawScaleBack(g);
        if(model.vecY.isEmpty() || model.vecX.isEmpty()) return;

        drawLineX_axis();
        drawLineY_axis();


    }
    private void drawLineX_axis(){
        g.setColor(Color.BLUE);
        g.drawLine(Math.round(model.Xa*scaleX) + posX, 0,
                 Math.round(model.Xa*scaleX) + posX, getHeight());
        g.drawString("Xa = " +model.Xa, Math.round(model.Xa* scaleX) + posX +10,getHeight());

        g.setColor(Color.GREEN);
        g.drawLine(Math.round((model.Xa-model.Sx)*scaleX) + posX, 0,
                Math.round((model.Xa-model.Sx)*scaleX) + posX, getHeight());
        g.drawString("Xa-Sx = " +(model.Xa-model.Sx) , Math.round((model.Xa-model.Sx)* scaleX) + posX +10,getHeight()-25);


        g.drawLine(Math.round((model.Xa+model.Sx)*scaleX) + posX, 0,
                Math.round((model.Xa+model.Sx)*scaleX) + posX, getHeight());
        g.drawString("Xa+Sx = " +(model.Xa+model.Sx), Math.round((model.Xa+model.Sx)* scaleX) + posX +10,getHeight()-50);

    }
    private void drawLineY_axis(){
        g.setColor(Color.BLUE);
        g.drawLine(0, getHeight() - (Math.round(model.Ya* scaleY) + posY),
                   getWidth()-150, getHeight() - (Math.round(model.Ya* scaleY) + posY));
        g.drawString("Ya="+model.Ya,0,getHeight() - (Math.round(model.Ya* scaleY) + posY)-4);

        g.setColor(Color.GREEN);
        g.drawLine(0, getHeight() - (Math.round((model.Ya-model.Sy)* scaleY) + posY),
                getWidth()-150, getHeight() - (Math.round((model.Ya-model.Sy)* scaleY) + posY));
        g.drawString("Ya-Sy="+(model.Ya-model.Sy),0,getHeight() - (Math.round((model.Ya-model.Sy)* scaleY) + posY)-4);

        g.drawLine(0, getHeight() - (Math.round((model.Ya+model.Sy)* scaleY) + posY),
                getWidth()-150, getHeight() - (Math.round((model.Ya+model.Sy)* scaleY) + posY));
        g.drawString("Ya+Sy="+(model.Ya+model.Sy),0,getHeight() - (Math.round((model.Ya+model.Sy)* scaleY) + posY)-4);

        g.setColor(Color.BLACK);
        g.drawString("R="+model.r,0,20);
    }

    private void drawScaleBack(Graphics g) {

        g.setColor(Color.PINK);

        for (int j = 0; j <= posY; j += scaleY) {

            //float yt = (posY-j)/scaleY;
            g.drawLine(0, j + posY, getWidth()-150, j + posY);
            g.drawLine(0, posY - j, getWidth()-150, posY - j);
            //g.drawString(String.format("%.2f",yt),posX,(int)j);

        }
        for (int j = 0; j <= posX; j += scaleX) {

            g.drawLine(j + posX, 0, j + posX, getHeight());
            g.drawLine(posX - j, 0, posX - j, getHeight());
        }

        g.setColor(Color.RED);
        g.drawLine(posX, 0, posX, getHeight());
        g.drawLine(0, posY, getWidth()-150, posY);

    }

    public void setInit(Model model)
    {
        this.model = model;

        scaleX = (int) Math.floor((double) (getWidth()-150)/ 22);
        scaleY = (int) Math.ceil((double) getHeight() / 22);

        posX = (getWidth()-150) / 2;
        posY = getHeight() / 2;

    }

}
