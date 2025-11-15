package org.example.entity;

import java.util.*;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    public Set<Task> getAnnsTasks() {
        return annsTasks;
    }

    public Set<Task> getBobsTasks() {
        return bobsTasks;
    }

    public Set<Task> getCarolsTasks() {
        return carolsTasks;
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    /** "ann", "bob", "carol", "all" (opsiyonel: "unassigned") */
    public Set<Task> getTasks(String who) {
        if (who == null) return Set.of();
        switch (who.toLowerCase(Locale.ROOT)) {
            case "ann":   return getAnnsTasks();
            case "bob":   return getBobsTasks();
            case "carol": return getCarolsTasks();
            case "all":
                return getUnion(List.of(annsTasks, bobsTasks, carolsTasks));
            case "unassigned":
                return getUnassignedTasks();
            default:
                return Set.of();
        }
    }

    /** Birden fazla çalışana atanmış tasklar */
    public Set<Task> getAssignedToMultiple() {
        Set<Task> ab = getIntersection(annsTasks, bobsTasks);
        Set<Task> ac = getIntersection(annsTasks, carolsTasks);
        Set<Task> bc = getIntersection(bobsTasks, carolsTasks);
        return getUnion(List.of(ab, ac, bc));
    }

    /** Tüm çalışanların üzerindeki tasklar (unassigned hariç) */
    public Set<Task> getAllAssigned() {
        return getUnion(List.of(annsTasks, bobsTasks, carolsTasks));
    }

    /* -------------------- Set yardımcıları -------------------- */

    public static Set<Task> getUnion(Collection<Set<Task>> sets) {
        Set<Task> out = new HashSet<>();
        if (sets != null) {
            for (Set<Task> s : sets) {
                if (s != null) out.addAll(s);
            }
        }
        return out;
    }
    public Set<Task> getUnion(Set<Task>... sets) {
        Set<Task> out = new java.util.HashSet<>();
        if (sets != null) {
            for (Set<Task> s : sets) {
                if (s != null) out.addAll(s);
            }
        }
        return out;
    }

    public static Set<Task> getIntersection(Set<Task> a, Set<Task> b) {
        if (a == null || b == null) return Set.of();
        Set<Task> out = new HashSet<>(a);
        out.retainAll(b);
        return out;
    }

    /** a \ b (a'dan b'de olanları çıkar) */
    public static Set<Task> getDifferences(Set<Task> a, Set<Task> b) {
        if (a == null) return Set.of();
        if (b == null) return new HashSet<>(a);
        Set<Task> out = new HashSet<>(a);
        out.removeAll(b);
        return out;
    }
}
