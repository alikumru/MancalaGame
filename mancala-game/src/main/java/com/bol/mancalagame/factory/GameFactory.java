package com.bol.mancalagame.factory;

import com.bol.mancalagame.service.GameService;
import com.bol.mancalagame.service.MultiPlayerGameService;
import com.bol.mancalagame.service.RegularGameService;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GameFactory {

    public static GameService getGameService(int numberOfPlayers,String typeOfPlayers){
        if(numberOfPlayers == 2)
            return new RegularGameService();
        else
            return new MultiPlayerGameService();
    }

}
