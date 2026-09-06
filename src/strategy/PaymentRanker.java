import java.util.*;

// Data class (given).
class PaymentMethod {
    public String name;
    public double cashbackRate;
    public double transactionFee;
    public int usageCount;
    public boolean easyRefundEligible;

    public PaymentMethod(String name, double cashbackRate, double transactionFee, int usageCount, boolean easyRefundEligible) {
        this.name = name;
        this.cashbackRate = cashbackRate;
        this.transactionFee = transactionFee;
        this.usageCount = usageCount;
        this.easyRefundEligible = easyRefundEligible;
    }

    public PaymentMethod(String name, double cashbackRate, double transactionFee, int usageCount) {
        this(name, cashbackRate, transactionFee, usageCount, false);
    }
}

interface RankingStrategy{
    public int compare(PaymentMethod a, PaymentMethod b);
}

class RewardsMaximizer implements RankingStrategy{
    @Override
    public int compare(PaymentMethod a,PaymentMethod b){
        return Double.compare(b.cashbackRate,a.cashbackRate);
    }
}
class LowFeeSeeker implements RankingStrategy{
    @Override
    public int compare(PaymentMethod a,PaymentMethod b){
        return Double.compare(a.transactionFee,b.transactionFee);
    }
}

class TrustBased implements RankingStrategy{
    @Override
    public int compare(PaymentMethod a,PaymentMethod b){
        return Integer.compare(b.usageCount,a.usageCount);
    }
}

public class Solution {
    public static List<PaymentMethod> rank_by_rewards(List<PaymentMethod> methods) {
        return new PaymentRanker(new RewardsMaximizer()).rank(methods);
    }

    public static List<PaymentMethod> rank_by_low_fee(List<PaymentMethod> methods) {
        return new PaymentRanker(new LowFeeSeeker()).rank(methods);
    }

    public static List<PaymentMethod> rank_by_trust(List<PaymentMethod> methods) {
        return new PaymentRanker(new TrustBased()).rank(methods);
    }
}
class PaymentRanker{
    private RankingStrategy s;
    public PaymentRanker(RankingStrategy s){
        this.s = s;
    }
    public List<PaymentMethod> rank(List<PaymentMethod> methods){
        methods.sort((a,b)->{
            return s.compare(a,b);
        });
        return methods;
    }
}
