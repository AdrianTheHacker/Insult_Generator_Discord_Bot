/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package discord_voting_bot;

import discord_voting_bot.discord_client.DiscordClient;
import discord_voting_bot.env_file_reader.EnvFileReader;


public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        String DISCORD_BOT_TOKEN = new EnvFileReader(".\\assets\\.env").getEnvironmentVariable("DISCORD_BOT_TOKEN");
        System.out.println(DISCORD_BOT_TOKEN);

        DiscordClient discordClient = new DiscordClient(DISCORD_BOT_TOKEN);
        discordClient.run();
    }
}
