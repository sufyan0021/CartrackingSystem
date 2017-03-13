
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.test.InstrumentationTestRunner;

import com.google.android.gms.samples.vision.ocrreader.MainActivity;
import com.google.android.gms.samples.vision.ocrreader.database.FraudVehicleDatabase;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.orm.util.SugarConfig;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockitoAnnotationsMockAnnotationProcessor;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by kanav on 3/9/2017.
 */
@RunWith(MockitoJUnitRunner.class)

public class VehicleListActivityTest{


    @Mock
    Context mMockContext;

    private String NUMBER = "TN AD 5604";
    private List<String> NUMBERLIST = new ArrayList<String>(Arrays.asList("JH O1T 0859", "JK 02AN 2019", "JK 02V 1008"));

    @Test
    public void testFraudListCreatorForOneValue() {
        // Given a mocked Context injected into the object under test...
        MockitoAnnotations.initMocks(this);
        SugarContext.init(mMockContext);
        FraudVehicleDatabase.deleteAll(FraudVehicleDatabase.class);
        FraudVehicleDatabase fraudVehicleDatabase = new FraudVehicleDatabase(NUMBER);
        fraudVehicleDatabase.save();
        assertEquals(NUMBER,FraudVehicleDatabase.listAll(FraudVehicleDatabase.class).get(0));
        FraudVehicleDatabase.deleteAll(FraudVehicleDatabase.class);
    }

    @Test
    public void testFraudListCreatorForList() {
        FraudVehicleDatabase.deleteAll(FraudVehicleDatabase.class);
        for(int i=0;i<NUMBERLIST.size();i++) {
            FraudVehicleDatabase fraudVehicleDatabase = new FraudVehicleDatabase(NUMBERLIST.get(i));
            fraudVehicleDatabase.save();
        }
        assertArrayEquals(NUMBERLIST.toArray(),FraudVehicleDatabase.listAll(FraudVehicleDatabase.class).toArray());
        FraudVehicleDatabase.deleteAll(FraudVehicleDatabase.class);
    }

    @Test
    public void testVehicleListCreator() {
        assertEquals(1,1);
    }
}
