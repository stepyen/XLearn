package com.stepyen.xlearn.activity.DesignPattern;

/**
 * date：2019/8/5
 * author：stepyen
 * description：建造者模式
 */
public class JavaBuilder {
    public String name;
    public String age;

    private JavaBuilder(Builder builder) {
        name = builder.name;
        age = builder.age;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String age;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public JavaBuilder build() {
            return new JavaBuilder(this);
        }
    }
}
