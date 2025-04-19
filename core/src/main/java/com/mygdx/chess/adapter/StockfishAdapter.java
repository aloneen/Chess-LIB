package com.mygdx.chess.adapter;

import com.badlogic.gdx.Gdx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter wrapping Stockfish via UCI protocol.
 */
public class StockfishAdapter implements IBotEngine {
    private Process engine;
    private BufferedWriter engineIn;
    private BufferedReader engineOut;
    private List<String> history;

    public StockfishAdapter(String enginePath, int elo) {
        try {
            engine = new ProcessBuilder(enginePath)
                .redirectErrorStream(true)
                .start();
            engineIn  = new BufferedWriter(new OutputStreamWriter(engine.getOutputStream()));
            engineOut = new BufferedReader(new InputStreamReader(engine.getInputStream()));

            send("uci");
            send("setoption name UCI_LimitStrength value true");
            send("setoption name UCI_Elo value " + elo);
            send("isready");
        } catch (Exception e) {
            Gdx.app.error("StockfishAdapter", "Failed to launch engine", e);
        }
    }

    @Override
    public void setMoveHistory(List<String> history) {
        this.history = history;
    }

    @Override
    public String getBestMove() throws Exception {
        String moves = history == null ? "" : String.join(" ", history);
        send("position startpos moves " + moves);
        send("go movetime 500");
        String line;
        while ((line = engineOut.readLine()) != null) {
            if (line.startsWith("bestmove")) {
                return line.split(" ")[1];
            }
        }
        return null;
    }

    private void send(String cmd) throws Exception {
        engineIn.write(cmd + "\n");
        engineIn.flush();
    }

    @Override
    public void dispose() {
        try { if (engine != null) engine.destroy(); }
        catch (Exception ignored) {}
    }
}

