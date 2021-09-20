package com.bol.mancalagame.publisher;

import com.bol.mancalagame.model.GameData;
import org.springframework.stereotype.Component;

import java.util.concurrent.SubmissionPublisher;

@Component
public class GamePublisher extends SubmissionPublisher<GameData>  {
}
