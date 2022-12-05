package java_discord_bot.env_reader;

import io.github.cdimascio.dotenv.Dotenv;


public class EnvFileReader {
    private Dotenv dotEnv;


    public EnvFileReader(String EnvDirectoryPath) {
        this.dotEnv = Dotenv.configure()
                            .directory(EnvDirectoryPath)
                            .load();
    }

    public String getEnvironmentVariable(String environmentVariableName) {
        return this.dotEnv.get(environmentVariableName);
    }
}
