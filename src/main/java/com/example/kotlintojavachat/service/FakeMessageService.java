package com.example.kotlintojavachat.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.example.kotlintojavachat.MessageVM;
import com.example.kotlintojavachat.UserVM;
import com.github.javafaker.Faker;

@Service
public class FakeMessageService implements MessageService
{
    Map<String, UserVM> users;
    Map<String, String> usersQuotes;
    Random random;
    public FakeMessageService() throws MalformedURLException
    {
        users = Map
                .of(
                        "Shakespeare", new UserVM("Shakespeare", new URL("https://blog.12min" +
                                ".com/wp-content/uploads/2018/05/27d-William-Shakespeare.jpg")),
                        "RickAndMorty", new UserVM("RickAndMorty", new URL("http://thecircular" +
                                ".org/wp-content/uploads/2015/04/rick-and-morty-fb-pic1.jpg")),
                        "Yoda", new UserVM("Yoda", new URL("https://news.toyark" +
                                ".com/wp-content/uploads/sites/4/2019/03/SH-Figuarts-Yoda-001.jpg")));

        usersQuotes = Map
                .of("Shakespeare", Faker.instance().shakespeare().asYouLikeItQuote(),
                        "RickAndMorty", Faker.instance().rickAndMorty().quote(),
                        "Yoda", Faker.instance().yoda().quote());

        random = new Random();
    }

    public List<MessageVM> latest() {
        List<MessageVM> messageVMS = new ArrayList<>();

        int count = random.nextInt( 15);
        for(int i=0; i < count; i++)
        {
            List<String> keys = new ArrayList<>(users.keySet());
            String randKey = keys.get(random.nextInt(keys.size()));
            String content = usersQuotes.get(randKey);
            messageVMS.add(new MessageVM(content, users.get(randKey), Instant.now(), Integer.toString(random.nextInt(10))));
        }
        return messageVMS;
    }

    public List<MessageVM> after(String messageId)
    {
        return latest();
    }

    @Override
    public void post(MessageVM messageVM)
    {
        //TODO
    }
}
