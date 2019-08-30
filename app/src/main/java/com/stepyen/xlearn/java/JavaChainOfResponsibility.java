package com.stepyen.xlearn.java;

/**
 * date：2019/8/5
 * author：stepyen
 * description：责任链模式
 *
 * 接收者形成链，层层下传
 */
public class JavaChainOfResponsibility {
    public static abstract class AbstractLogger {
        public static int A = 1;
        public static int B = 2;
        public static int C = 3;

        protected int level;

        //责任链中的下一个元素
        protected AbstractLogger nextLogger;

        public void setNextLogger(AbstractLogger nextLogger){
            this.nextLogger = nextLogger;
        }

        public void logMessage(int level, String message){
            if(this.level <= level){
                write(message);
            }
            if(nextLogger !=null){
                nextLogger.logMessage(level, message);
            }
        }

        abstract protected void write(String message);

    }

    public static class ALogger extends AbstractLogger {

        public ALogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("ALogger: " + message);
        }
    }

    public static class BLogger extends AbstractLogger {

        public BLogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("BLogger: " + message);
        }
    }

    public static class CLogger extends AbstractLogger {

        public CLogger(int level){
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("CLogger: " + message);
        }
    }


    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger aLogger = new ALogger(AbstractLogger.A);
        AbstractLogger bLogger = new BLogger(AbstractLogger.B);
        AbstractLogger cLogger = new CLogger(AbstractLogger.C);

        aLogger.setNextLogger(bLogger);
        bLogger.setNextLogger(cLogger);

        return aLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.A,
                "This is an a.");

        loggerChain.logMessage(AbstractLogger.B,
                "This is an b");

        loggerChain.logMessage(AbstractLogger.C,
                "This is an c");
    }
}
