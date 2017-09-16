package controllers;

import play.mvc.*;

import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private ExecutionContextExecutor ec;

    @Inject
    public HomeController(ExecutionContextExecutor ec) {
        this.ec = ec;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>admin.routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public CompletionStage<Result> completionStage(String pattern) {
        final String uuid = UUID.randomUUID().toString();

        final List<String> list = appendThreadName(new ArrayList<>(), pattern);

        switch (pattern) {
            case "1":
                return CompletableFuture
                        .supplyAsync(() -> {
                            sleep(100);
                            return appendThreadName(list, pattern + "-1 async");
                        }, ec)
                        .thenApplyAsync(l -> {
                            sleep(100);
                            return appendThreadName(l, pattern + "-2 async");
                        }, ec)
                        .thenApply(l -> {
                            sleep(100);
                            return ok(appendThreadName(l, pattern + "-3").stream().collect(Collectors.joining("\n")));
                        });
            case "2":
                return CompletableFuture
                        .supplyAsync(() -> {
                            sleep(100);
                            return appendThreadName(list, pattern + "-1 async");
                        }, ec)
                        .thenApply(l -> {
                            sleep(100);
                            return appendThreadName(l, pattern + "-2");
                        })
                        .thenApply(l -> ok(appendThreadName(l, pattern + "-3").stream().collect(Collectors.joining("\n"))));
            case "3":
                return CompletableFuture
                        .supplyAsync(() -> {
                            sleep(100);
                            return appendThreadName(list, pattern + "-1");
                        }, ec)
                        .thenApply(l -> {
                            sleep(100);
                            return ok(appendThreadName(l, pattern + "-2").stream().collect(Collectors.joining("\n")));
                        });
            case "4":
                return CompletableFuture
                        .supplyAsync(() -> {
                            sleep(100);
                            return appendThreadName(list, pattern + "-1");
                        })
                        .thenApply(l -> {
                            sleep(100);
                            return ok(appendThreadName(l, pattern + "-2").stream().collect(Collectors.joining("\n")));
                        });

            default:
                return CompletableFuture.completedFuture(badRequest("bad pattern"));
        }

    }

    private List<String> appendThreadName(List<String> threadNameList, String message) {
        List<String> l = new ArrayList<>(threadNameList);
        l.add(Thread.currentThread().getName() + ":" + message);
        return Collections.unmodifiableList(l);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
