package discord_voting_bot.discord_client;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;


public class DiscordBot {
    private DiscordApi api;


    public DiscordBot(String discordBotToken) {
        this.api = new DiscordApiBuilder()
            .setToken(discordBotToken)     
            .addIntents(Intent.MESSAGE_CONTENT)
            .addIntents(Intent.GUILD_EMOJIS)
            .login()
            .join();

        this.api.updateActivity(ActivityType.COMPETING, "in the stock market");
    }

    private void buildSlashCommands() {
        SlashCommand command = SlashCommand.with("hello", "A friendly way to greet the bot")
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