The build() and perform() methods within the Actions class in Selenium WebDriver are 
used to simulate user interactions. 

The build() method is used to compile a sequence of actions into a single Action object. 
However, some Actions class methods automatically incorporate the build() process 
within them, allowing you to directly call perform().

When you see actions.doubleClick(element).build().perform();, 
build() is explicitly called because this is a general usage pattern 
where you can chain multiple actions together and then execute them all at once 
with perform().

On the other hand, 
in builder.dragAndDrop(source, target).perform();, 
you don’t need to call build() because the dragAndDrop method 
already includes the build process and returns an Action object. 
Therefore, you can directly invoke perform() with such methods.

In essence, whether you need to explicitly call build() 
depends on the internal structure of the Actions methods you are using. 
Most of the time, you can skip build() and directly use perform(). 
However, if you are creating complex sequences of actions and want to combine 
multiple actions, you need to use build() to compile them into an Action object 
before executing them with perform().

For example:

Actions builder = new Actions(driver);
builder.moveToElement(someElement).click().build().perform();

This usage shows that you need to explicitly use the build() method 
when you want to perform a sequence of actions in order. 
For a single action, there is no need to call build().