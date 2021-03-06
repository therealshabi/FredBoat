package fredboat.agent;

import fredboat.audio.GuildPlayer;
import fredboat.audio.PlayerRegistry;
import java.util.HashMap;
import net.dv8tion.jda.JDA;
import org.slf4j.LoggerFactory;

public class MusicGC extends Thread {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MusicGC.class);
    private final JDA jda;

    public MusicGC(JDA jda) {
        super();
        this.jda = jda;
    }

    @Override
    public void run() {
        log.info("Music GC running");
        while(true){
            synchronized(this){
                try {
                    this.sleep(60000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                
                HashMap<String, GuildPlayer> players = PlayerRegistry.getRegistry();
                
                for(GuildPlayer player : players.values()){
                    
                    if(player.isPaused()
                            && player.isStopped() == false
                            && player.getMillisSincePause() > 60000){
                        player.stop();
                        log.info("Stopped player: " + player);
                    }
                    
                    if(player.getChannel() != null){
                        player.markIsInVC();
                    } else if(player.isStopped() == false
                            && player.getMillisSinceInVC() > 300000) {
                        player.stop();
                        log.info("Stopped player for not being in a VC last 5 minutes: " + player);
                    }
                }
            }
        }
    }
    
}
