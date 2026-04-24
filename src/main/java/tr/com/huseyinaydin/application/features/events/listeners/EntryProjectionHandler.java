package tr.com.huseyinaydin.application.features.events.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.application.features.events.EntryVoteChangedEvent;

@Component
@RequiredArgsConstructor
public class EntryProjectionHandler {

    @Async
    @EventListener
    public void handleEntryVoteChanged(EntryVoteChangedEvent event) {
        // .NET tarafındaki Projeksiyon mantığı burada simüle edilir.
        // Örneğin: Veritabanında Entry tablosundaki toplam oy sayısını güncelleyen bir SQL tetiklenebilir
        // veya bir ReadModel (Elasticsearch, Redis vb.) güncellenebilir.
        System.out.println("[PROJECTION] Entry vote changed for: " + event.getEntryId());
    }
}
