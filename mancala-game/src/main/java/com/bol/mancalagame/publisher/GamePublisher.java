package com.bol.mancalagame.publisher;

import com.bol.mancalagame.model.Game;
import org.springframework.stereotype.Component;

import java.util.concurrent.SubmissionPublisher;

@Component
public class GamePublisher extends SubmissionPublisher<Game>  {
}
