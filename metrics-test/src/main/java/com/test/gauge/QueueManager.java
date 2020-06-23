package com.test.gauge;

import static com.codahale.metrics.MetricRegistry.name;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {
  private final Queue queue;
  private final Counter pendingJobs;

  public QueueManager(MetricRegistry metrics, String name) {
    this.queue = new LinkedList();
    metrics.register(
        name(QueueManager.class, name, "size"),
        new Gauge<Integer>() {
          @Override
          public Integer getValue() {
            return queue.size();
          }
        });

    pendingJobs = metrics.counter(name(QueueManager.class, "pending-jobs"));
  }

  public void addJob(String job) {
    pendingJobs.inc();
    queue.offer(job);
  }

  public String takeJob() {
    pendingJobs.dec();
    return (String) queue.poll();
  }
}
