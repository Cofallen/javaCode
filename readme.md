## Java 速查

### Object 用法

在 Java 中，Object 是所有类的基类，因此所有对象都可以被视为 Object 类型。这种灵活性使得 Object 类在某些情况下非常有用，特别是在需要处理未知类型的数据时。

下面是一些关于 Java 中 Object 类的基本用法：

1. **创建对象**：可以通过实例化一个类来创建对象，例如：
```java
Object obj = new Object();
```

2. **方法调用**：Object 类中包含了一些常用的方法，比如 `toString()`, `equals()`, `hashCode()` 等。这些方法可以被所有对象继承和使用。

3. **类型转换**：由于 Object 是所有类的父类，你可以将任何对象转换为 Object 类型，然后再进行类型转换，例如：
```java
Object obj = "Hello";
String str = (String) obj;
```

4. **泛型中的应用**：在泛型中，可以使用 Object 类型来实现泛型类的通用性，例如：
```java
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

5. **对象数组**：可以创建 Object 类型的数组来存储不同类型的对象，但需要进行类型转换来使用具体类型的方法和属性。

Object 类在 Java 中扮演着重要的角色，尤其在处理**未知类型**的数据或需要通用性的情况下非常有用。

### int Interger 区别

在 Java 中，`Integer` 和 `int` 是两种不同的数据类型，它们之间有一些区别：

1. **包装类 vs 基本数据类型**:
   - `int` 是 Java 的基本数据类型，用于表示整数值。
   - `Integer` 是 `int` 的包装类，它是一个对象，可以将 `int` 装箱为 `Integer` 对象。

2. **空值处理**:
   - `int` 是基本数据类型，不能表示空值（null）。
   - `Integer` 是一个对象，可以为其赋予空值（null）。

3. **自动装箱和拆箱**:
   - Java 提供了**自动装箱**（autoboxing）和**自动拆箱**（unboxing）功能，可以在 `int` 和 `Integer` 之间进行自动转换。
   - 例如，你可以直接将 `int` 赋值给 `Integer`，系统会**自动进行装箱操作**。

4. **集合使用**:
   - 在集合类中，只能存储对象，因此如果要将 `int` 存储在集合中，需要使用 `Integer` 对象。
   - 例如，`ArrayList<Integer>` 可以存储 `Integer` 对象，而不是 `int`。

总的来说，`int` 是基本数据类型，而 `Integer` 是 `int` 的包装类，提供了更多的功能和灵活性，但在性能上可能会有一些差异。在需要对象而不是基本数据类型的情况下，可以使用 `Integer`。

### Compare 接口

`Comparable` 接口是一个泛型接口，可以**接受任何实现了 `Comparable` 接口的类**作为类型参数。`Integer` 类实现了 `Comparable` 接口，因此可以将 `Integer` 类型的对象传递给实现了 `Comparable` 接口的方法或类。

### new 生命周期

在 Java 中，使用 `new` 关键字在函数内部创建对象时，对象的生命周期取决于对象的引用。如果在函数内部使用 `new` 创建对象，并将该对象的引用返回或传递给其他作用域，则对象的生命周期将延长到超出函数范围的地方。如果对象的引用仅在函数内部使用，并且函数执行完毕后没有其他引用指向该对象，那么该对象将在函数执行完毕后被Java的垃圾回收机制回收，释放内存空间。
