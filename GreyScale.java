/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GreyScale;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Larinh
 */
public class GreyScale {
    private BufferedImage biOriginal;
    private BufferedImage biResult;
    
    public GreyScale() { 
        this.init(); }
    
    public BufferedImage getBiOriginal() {
	    return biOriginal;}

    public void setBiOriginal(BufferedImage biOriginal) {
	    this.biOriginal = biOriginal;}
   
    public BufferedImage getbiResult() {
        return biResult;}

    public void setbiResult(BufferedImage biResult) {
        this.biResult = biResult;}
    
    public BufferedImage doWork() {
	this.setbiResult( new BufferedImage(this.getBiOriginal().getWidth(), this.getBiOriginal().getHeight(), this.getBiOriginal().getType()) );
	int xIni = 0;
	int xEnd = this.getBiOriginal().getHeight(null) - 1;			
	while( true ) {
		if( xEnd < xIni ) {
		break;}
		int yIni = 0;
		int yEnd = this.getBiOriginal().getWidth(null) - 1;
		while( true ) {
                    if( yEnd < yIni ) {
                    break;}
                    Color colorSE = this.calculate( yIni, xIni );
                    this.getbiResult().setRGB( yIni, xIni, colorSE.getRGB() );
                    Color colorSD = this.calculate( yIni, xEnd );
                    this.getbiResult().setRGB( yIni, xEnd, colorSD.getRGB());
                    Color colorIE = this.calculate( yEnd, xIni );
                    this.getbiResult().setRGB( yEnd, xIni, colorIE.getRGB() );
                    Color colorID = this.calculate( yEnd, xEnd );
                    this.getbiResult().setRGB( yEnd, xEnd, colorID.getRGB());
                    yIni++;
                    yEnd--;
                    }
		xIni++;
		xEnd--;
		}
  	 return this.getbiResult();
    }

    private void init() {
  	this.setBiOriginal( null );
  	this.setbiResult( null );
  	}

    private Color calculate( int x, int y ) {        
	   Color color = new Color(this.getBiOriginal().getRGB(x, y));
	   int grey = (int) (0.2989*color.getRed() + 0.5870*color.getGreen() + 0.1140*color.getBlue());
	   return new Color(grey, grey, grey);	
    }
    
 
    private int calculate2( int x, int y ) {        
        int rgb = this.getBiOriginal().getRGB(x, y);
	int r = (rgb >> 16) & 0xff;
	int g = (rgb >> 8) & 0xff;
	int b = (rgb >> 0) & 0xff;
	r = (int)( r * 0.2989 );
	g = (int)( g * 0.5870 );
	b = (int)( b * 0.1140 );
	int grey = r + g + g;
	rgb = (rgb & 0xff000000)  | ( grey << 16 ) | ( grey << 8 ) | ( grey << 0 );
	return rgb;	
    }     
}
