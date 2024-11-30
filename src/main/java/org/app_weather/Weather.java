package org.app_weather;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;


import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;



public class Weather {
    private double progress=0;// переменная для progressBar from Controller
    Thread thread;  // отдельный поток для "асинхронного запроса"
    final String  API_KEY ="945aeac3143791440eea96eb52cfbbea";
    final String BASE_URL=  "https://api.openweathermap.org/data/2.5/weather";

    PropertyChangeSupport psc = new PropertyChangeSupport(this);
    public void addListener(PropertyChangeListener listener){
        psc.addPropertyChangeListener(listener);
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String newCity) {
        this.nameCity = newCity;
    }
    public Weather(){}

    private String nameCity;
    private String jsonData;
    public String strWeather, strError;
    Object objJsonData;
    public void setJsonData(String newJsonData) {
        String oldJasonData = this.jsonData;
        this.jsonData = newJsonData;
        psc.firePropertyChange("jasonData",oldJasonData,newJsonData);
    }

    public String getJsonData() {
        return jsonData;
    }
    OkHttpClient okHttpClient = new OkHttpClient();
    public JSONObject jsonObject,main,weather,wind;
    Request request;
    public double getProgress() {
        return progress;
    }

    public void setProgress(double newValue) {
        double oldValue = progress;
        this.progress = newValue;
        psc.firePropertyChange("progress",oldValue,newValue);
    }
    public void getWeather() throws Exception
    {
        String url = BASE_URL + "?q=" + getNameCity() + "&appid="+API_KEY+ "&units=metric&lang=ru-RU";
        request = new Request.Builder()  // создаём http запрос
                .url(url)
                .build();

        while (getProgress()<1) // имитируем "прогресс"  при получение данных от сервера
        {
            Thread.sleep(150);
            setProgress((double)getProgress()+0.05);
        }
        try(Response response = okHttpClient.newCall(request ).execute()){// выполняем синхронно
           if(response.isSuccessful()&&response.body()!=null){ setJsonData(response.body().string()); objJsonData =(response.body());}
           else setJsonData("Error "+response.code()+" "+ response.message());
        }catch (Exception e){strError = e.getMessage();}

//        try
//        {     okHttpClient.newCall(request).enqueue(new Callback() {// асинхронный запрос
//              @Override
//              public void onFailure(@NotNull Call call, @NotNull IOException e) {
//              e.printStackTrace();
//              }
//
//              @Override
//              public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                  if(response.isSuccessful()&&response.body()!=null){ setJsonData(response.body().string()); objJsonData =(response.body());}
//                  else setJsonData("Error "+response.code()+" "+ response.message());
//              }
//
//          });
//        }catch (Exception e){strError ="okHttpClient.newCall(request).enqueue(new Callback()" +e.getMessage()+" ";}
//        finally {  }
    }
    double temp, pressure,speedWind; // переменные для парсинга json
    int humidity, visibility;
    String description;



    void parserWeather()
    {
           try
            {
                if(!getJsonData().isEmpty()){
                jsonObject = new JSONObject(getJsonData());
                main  = jsonObject.getJSONObject("main");
                weather = jsonObject.getJSONArray("weather").getJSONObject(0);
                visibility = jsonObject.getInt("visibility");
                wind = jsonObject.getJSONObject("wind");
                speedWind = wind.getDouble("speed");
                temp = main.getDouble("temp");
                humidity = main.getInt("humidity");
                pressure = main.getDouble("pressure");
                description = weather.getString("description");

                }

                strWeather = "Температура: "+temp+"\nВлажность: "
                    + humidity + "\nДавление: "+pressure+"\nСкорость ветра: "+speedWind
                        +"\nВидимость: "+ visibility +"\nОписание: "+description
                       + (strError!=null? "\nОшибок: "+strError:"");


            }
            catch (Exception e){strError+=" \nparserWeather() "+e.getMessage()+" ";}


    }

    public  void getStrWeather()
    {
        try
        {   thread = new Thread(()->{
             try{ getWeather();}
             catch (Exception e){strError+=" \n getStrWeather() "+e.getMessage()+" "; }
            });
            thread.start();
        }
        catch(Exception e){strError+=" \n getStrWeather() "+e.getMessage()+" ";}
        finally { }
    }
}
