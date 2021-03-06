
/**
  * Run a computer simulation for flipping 1,000 virtual fair coins. Flip each coin inde-
    pendently 10 times. Focus on 3 coins as follows: c 1 is the first coin flipped, c rand is a
    coin chosen randomly from the 1,000, and c min is the coin which had the minimum
    frequency of heads (pick the earlier one in case of a tie). Let ν 1 , ν rand , and ν min be
    the fraction of heads obtained for the 3 respective coins out of the 10 tosses.
    Run the experiment 100,000 times in order to get a full distribution of ν 1 , ν rand , and
    ν min (note that c rand and c min will change from run to run).

    1. The average value of ν min is closest to:
    [a] 0
    [b] 0.01
    [c] 0.1
    [d] 0.5
    [e] 0.67

    2. Which coin(s) has a distribution of ν that satisfies Hoeffding’s Inequality?
    [a] c 1 only
    [b] c rand only
    [c] c min only
    [d] c 1 and c rand
    [e] c min and c rand
  */

/**
  * nu 1, nu rand, nu min 对应的 mu 1, mu rand, mu min 应该是 0.5, 0.5, 0.5
  *
  * My answer:
  * 1. b
  * 2. d （这个确实是如此，因为nu min是人为刻意挑选的，违背了Hoeffding Inequality的随机挑选的原则）
   */


object Hw2 {

  def main(args: Array[String]) = {
//    Question1and2.run()
    Question5to7.run()
  }


}
