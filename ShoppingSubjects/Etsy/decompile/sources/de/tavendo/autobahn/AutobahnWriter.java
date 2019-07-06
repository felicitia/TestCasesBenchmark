package de.tavendo.autobahn;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import de.tavendo.autobahn.a.C0177a;
import de.tavendo.autobahn.a.c;
import de.tavendo.autobahn.a.d;
import de.tavendo.autobahn.a.e;
import de.tavendo.autobahn.a.f;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MappingJsonFactory;

public class AutobahnWriter extends WebSocketWriter {
    private static final boolean DEBUG = true;
    private static final String TAG = "de.tavendo.autobahn.AutobahnWriter";
    private final JsonFactory mJsonFactory = new MappingJsonFactory();
    private final c mPayload = new c();

    public AutobahnWriter(Looper looper, Handler handler, SocketChannel socketChannel, h hVar) {
        super(looper, handler, socketChannel, hVar);
        Log.d(TAG, "created");
    }

    /* access modifiers changed from: protected */
    public void processAppMessage(Object obj) throws WebSocketException, IOException {
        this.mPayload.reset();
        JsonGenerator createJsonGenerator = this.mJsonFactory.createJsonGenerator(this.mPayload);
        try {
            if (obj instanceof C0177a) {
                C0177a aVar = (C0177a) obj;
                createJsonGenerator.writeStartArray();
                createJsonGenerator.writeNumber(2);
                createJsonGenerator.writeString(aVar.a);
                createJsonGenerator.writeString(aVar.b);
                for (Object writeObject : aVar.c) {
                    createJsonGenerator.writeObject(writeObject);
                }
                createJsonGenerator.writeEndArray();
            } else if (obj instanceof c) {
                c cVar = (c) obj;
                createJsonGenerator.writeStartArray();
                createJsonGenerator.writeNumber(1);
                createJsonGenerator.writeString(cVar.a);
                createJsonGenerator.writeString(cVar.b);
                createJsonGenerator.writeEndArray();
            } else if (obj instanceof e) {
                e eVar = (e) obj;
                createJsonGenerator.writeStartArray();
                createJsonGenerator.writeNumber(5);
                createJsonGenerator.writeString(eVar.a);
                createJsonGenerator.writeEndArray();
            } else if (obj instanceof f) {
                f fVar = (f) obj;
                createJsonGenerator.writeStartArray();
                createJsonGenerator.writeNumber(6);
                createJsonGenerator.writeString(fVar.a);
                createJsonGenerator.writeEndArray();
            } else if (obj instanceof d) {
                d dVar = (d) obj;
                createJsonGenerator.writeStartArray();
                createJsonGenerator.writeNumber(7);
                createJsonGenerator.writeString(dVar.a);
                createJsonGenerator.writeObject(dVar.b);
                createJsonGenerator.writeEndArray();
            } else {
                throw new WebSocketException("invalid message received by AutobahnWriter");
            }
            createJsonGenerator.flush();
            sendFrame(1, true, this.mPayload.a(), 0, this.mPayload.size());
            createJsonGenerator.close();
        } catch (JsonGenerationException e) {
            StringBuilder sb = new StringBuilder("JSON serialization error (");
            sb.append(e.toString());
            sb.append(")");
            throw new WebSocketException(sb.toString());
        } catch (JsonMappingException e2) {
            StringBuilder sb2 = new StringBuilder("JSON serialization error (");
            sb2.append(e2.toString());
            sb2.append(")");
            throw new WebSocketException(sb2.toString());
        }
    }
}
