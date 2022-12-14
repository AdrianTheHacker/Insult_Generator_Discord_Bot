package discord_voting_bot.discord_client;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;


public class DiscordClient {
    private DiscordApi api;


    public DiscordClient(String discordBotToken) {
        this.api = new DiscordApiBuilder()
            .setToken(discordBotToken)     
            .addIntents(Intent.MESSAGE_CONTENT)
            .addIntents(Intent.GUILD_EMOJIS)
            .addIntents(Intent.GUILD_MESSAGE_REACTIONS)
            .login()
            .join();

        this.api.updateActivity(ActivityType.COMPETING, "in the stock market");
    }

    private void buildSlashCommands() {
        SlashCommand command = SlashCommand.with("Insult Me", "I will make you cry")
            .createGlobal(this.api)
            .join();
    }
        
    private void slashCommandListener() {
        this.api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            if (interaction.getFullCommandName().equals("hello")) {
                System.out.println("Hello, world!");
                event.getInteraction()
                     .createImmediateResponder()
                     .setContent("Hello")
                     .respond();
            }
        });
    }

    public void run() {
        this.buildSlashCommands();
        this.slashCommandListener();
    }
}