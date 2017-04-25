package com.reszy.searchflix;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * React server side redraw class
 * Created by Rafał on 23.04.2017.
 */
public class React {
    private ThreadLocal<NashornScriptEngine> engineHolder = new ThreadLocal<NashornScriptEngine>() {
        @Override
        protected NashornScriptEngine initialValue() {
            NashornScriptEngine nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
            try {
                nashorn.eval(read("static/nashornSetup.js"));
                nashorn.eval(read("static/vendor/react.js"));
                nashorn.eval(read("static/components.js"));
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
            return nashorn;
        }
    };

    public  String renderMoviesList(List<MovieEntry> movies) {
        try {
            Object html = engineHolder.get().invokeFunction("renderServer", movies);
            return String.valueOf(html);
        }
        catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
