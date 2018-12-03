package org.meng.java9.module_b;

import org.meng.java9.module_a.model.User;

import java.util.Optional;

public class Demo {
    public static void main(String[] args) {
        User user = new User(1, "Meng", "Sun");
        System.out.println(user.toString());

        Optional<String> currUser = ProcessHandle.current().info().user();
        ProcessHandle.allProcesses()
                .filter(p1 -> p1.info().user().equals(currUser))
                .sorted(Demo::parentComparator)
                .forEach(Demo::showProcess);

    }

    static int parentComparator(ProcessHandle p1, ProcessHandle p2) {
        long pid1 = p1.parent().map(ph -> ph.pid()).orElse(-1L);
        long pid2 = p2.parent().map(ph -> ph.pid()).orElse(-1L);
        return Long.compare(pid1, pid2);
    }

    static void showProcess(ProcessHandle ph) {
        ProcessHandle.Info info = ph.info();
        System.out.printf("pid: %d, user: %s, cmd: %s%n",
                ph.pid(), info.user().orElse("none"), info.command().orElse("none"));
    }
}
