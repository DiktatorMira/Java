package itstep.learning.async;

public class AsyncDemo {
    private double sum;
    private final Object sumLock = new Object();

    public void run() {
        System.out.println("Async demo");
        percentDemo();
    }
    private void ThreadDemo() {
        Thread thread = new Thread(
            new Runnable() {
                @Override public void run() {
                    System.out.println("Hello Thread");
                }
            }
        );
        thread.start();     // ассинхронный запуск
        // thread.run();    // синхронный запуск
        System.out.println("1 Hello Main");
        System.out.println("2 Hello Main");
        System.out.println("3 Hello Main");
        System.out.println("4 Hello Main");
        System.out.println("5 Hello Main");
        System.out.println("6 Hello Main");
        System.out.println("7 Hello Main");
    }
    private void percentDemo() {
        sum = 100.0;
        for (int i = 1; i <= 12; i++)  new Thread(new Rate(i)).start();
    }

    private class Rate implements Runnable {
        private final int month;
        public Rate(int month) { this.month = month; }

        @Override public void run() {
            System.out.println("Rate " + month + " started");
            double percent;
            try {
                Thread.sleep(500); // имитация запроса
                percent = 10.0;
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                return;
            } synchronized (sumLock) {
                sum = sum * (1 + percent / 100.0);
                System.out.println("Ставка " + month + " закончила с суммой " + sum);
            }
        }
    }
}
/*  Многопоточность – программирование с использованием объектов системного типа – Thread.
    Объекты принимают в конструктор другие функциональные объекты интерфейсы.
    (В Java функциональными интерфейсами называют интерфейсы, в
    которых декларирован только один метод)
    Анонимный имплементирующий тип Runnable переопределяет его метод и инстанцируется (становится объектом)
    Традиционно для Java, создание нового объекта (thread) не создает сам поток, а только программную сущность

                Асинхронное программирование.
    Синхронность – последовательное во времени выполнения частей кода.
        ---------- ===========
    Асинхронность = любое отклонение от синхронности.
        ----------    - - - - - - -       -- -- -
        ==========      = = = = = = = =     =   = ====

                        Реализации

        - многозадачность: использование объектов уровня языка программирования/платформ
            (как-то Promise, Task, Future, Coroutine и так далее)
        - многопоточность: использование системных ресурсов – потоков (если они существуют в системе)
        - многопроцессность: использование системных ресурсов - процессов
        - сетевые технологии = grid = network

      Задачи, которые выгодно решать в асинхронном режиме, это "переставные"
      задачи, в которых порядок учета их частей не играет роли. Например, задачи
      сложение или умножение чисел.
      Пример:
       Нац. банк публикует процентные значения инфляции каждый месяц. Необходимо
       определить годовую инфляцию
       ? можно ли учитывать проценты в произвольном порядке?
       (100 + 10%) + 20% =? = (100 + 20%) + 10%
       (100 x 1.1) x 1.2 =?= (100 x 1.2) x 1.1
       100 x 1.1 x 1.2 =!= 100 x 1.2 x 1.1
       Да, можно. Замечание – при учете процентов 5-го месяца мы не
       гарантируем, что это инфляция на 5-й месяц, гарантируется только общий
       результат после учета всех составляющих. */