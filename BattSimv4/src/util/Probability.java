package util;

/**
 * Created by lvm on 3/8/14.
 */
/**
 public final class Probability {
 public final static Probability get = new Probability();

 /**
 * Input a length of
 *
 * @param numbers
 * @return
//-----ENDJAVADOCHERE-----
public static int probability(double... numbers) {

if (numbers.length > 1) {
double sum = 0;
for (double instance : numbers) {
sum += instance;
}
for (int i = 0; i < numbers.length; i++) {
numbers[i] /= sum;
}
double randomNumber = Math.random();
double progression = 0;
for (int i = 0; i < numbers.length; i++) {
boolean control = true;
if (control) {
if ((randomNumber > progression) && (randomNumber <= progression + numbers[i])) {
control = false;
return i;
} else {
progression += numbers[i];
}
return i;
}
}

}

}
 */
