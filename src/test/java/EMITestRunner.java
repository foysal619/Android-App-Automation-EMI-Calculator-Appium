import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EMITestRunner extends Setup {
    @BeforeTest
    public void startEMI() {
        EMIScreen emiScreen = new EMIScreen(driver);
        emiScreen.btnStart.click();
    }

    @Test(priority = 1, description = "EMI Calculate", dataProvider = "data-provider", dataProviderClass = Dataset.class)
    public void doCalculation(int loanAmount, double interestRate, int year, double processingFee, double resMonthlyEMI, double resTotalInterest, int resProcessingFee, double resTotalPayment) {
        EMIScreen emiScreen = new EMIScreen(driver);
        emiScreen.doCalculate(loanAmount, interestRate, year, processingFee);

        double actualMonthlyEMI = Double.parseDouble(emiScreen.lblMonthlyEMI.getText().replace(",",""));
        double actualTotalInterest = Double.parseDouble(emiScreen.lblTotalInterest.getText().replace(",",""));
        int actualProcessingFee = Integer.parseInt(emiScreen.lblProcessingFee.getText().replace(",",""));
        double actualTotalPayment = Double.parseDouble(emiScreen.lblTotalPayment.getText().replace(",",""));

        Assert.assertEquals(actualMonthlyEMI, resMonthlyEMI);
        Assert.assertEquals(actualTotalInterest, resTotalInterest);
        Assert.assertEquals(actualProcessingFee, resProcessingFee);
        Assert.assertEquals(actualTotalPayment, resTotalPayment);

        emiScreen.btnReset.click();

    }
}
