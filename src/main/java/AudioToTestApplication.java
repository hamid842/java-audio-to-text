import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AudioToTestApplication {
    public static void main(String[] args) throws Exception {

        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://raw.githubusercontent.com/johnmarty3/JavaAPITutorial/main/Thirsty.mp4");
//      Using Gson to convert to json
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);
        System.out.println(jsonRequest);

//      POST request to create transcript
        HttpRequest postRequest = HttpRequest.newBuilder() // this uses the builder pattern can add all the different elements of our request
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "2ac53bec91034b9fada160c8c04149ac")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest)) // ofString() : if we want to send json
                        .build(); // finish up the request

//        Send post request
        HttpClient httpClient = HttpClient.newHttpClient();
//        Store the response we get back
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString()); // 2nd parameter is for telling that we are expect a string response
        System.out.println(postResponse.body());

//        GET request to get transcript
        transcript = gson.fromJson(postResponse.body(),Transcript.class);
        System.out.println(transcript);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/"+transcript.getId()))
                .header("Authorization", "2ac53bec91034b9fada160c8c04149ac")
                .GET()
                .build();
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript);
            if("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                break;
            }
            Thread.sleep(1000);
        }
        System.out.println("Transcription completed.");
        System.out.println(transcript.getText());
    }
}
