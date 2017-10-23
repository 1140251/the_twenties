/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.twenties.traycontroller;



public class ConfigModel  {

    private int time;
    private String language;

    public ConfigModel(int time, String language) {
        this.time = time;
        this.language = language;
    }

    public ConfigModel() {
    }
  

   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfigModel other = (ConfigModel) obj;
        if (this.getTime() == other.getTime() && this.getLanguage().equals( other.getLanguage()) ) {
            return true;
        }
        return false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
