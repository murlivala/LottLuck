package mock.lottluck;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.Semaphore;

import mock.lottluck.callbacks.ServiceCallback;
import mock.lottluck.model.LottLuckItems;
import mock.lottluck.model.LottLuckMainModal;
import mock.lottluck.service.LottLuckListService;

import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LottLuckInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("mock.lottluck", appContext.getPackageName());
    }

    @Test
    public void testLottLuckListService() throws Exception {
        final Semaphore semaphore = new Semaphore(0);
        LottLuckListService lottLuckListService = new LottLuckListService(new ServiceCallback<LottLuckMainModal>() {
            @Override
            public void onSuccess(int status, Response<LottLuckMainModal> response) {
                assertNotNull(response);
                semaphore.release();
            }

            @Override
            public void onFailure(int status, String errorMessage) {
                semaphore.release();
                assertNotNull(null);
            }
        });
        lottLuckListService.start();
        semaphore.acquire();
    }


    @Test
    public void countPokemon() throws Exception {
        final Semaphore semaphore = new Semaphore(0);
        LottLuckListService lottLuckListService = new LottLuckListService(new ServiceCallback<LottLuckMainModal>() {
            @Override
            public void onSuccess(int status, Response<LottLuckMainModal> response) {
                LottLuckMainModal lottLuckMainModal = response.body();
                assertEquals(LottLuckTestData.TOTAL_LOTTS,lottLuckMainModal.getCompanies().size());
                semaphore.release();
            }

            @Override
            public void onFailure(int status, String errorMessage) {
                semaphore.release();
                assertNotNull(null);
            }
        });
        lottLuckListService.start();
        semaphore.acquire();
    }

    @Test
    public void checkFirstPokemonName() throws Exception {
        final Semaphore semaphore = new Semaphore(0);
        LottLuckListService lottLuckListService = new LottLuckListService(new ServiceCallback<LottLuckMainModal>() {
            @Override
            public void onSuccess(int status, Response<LottLuckMainModal> response) {
                List<LottLuckItems> lottLuckItems = response.body().getCompanies();
                assertEquals(LottLuckTestData.LOTT_ID_1,lottLuckItems.get(0).getCompanyId());
                semaphore.release();
            }

            @Override
            public void onFailure(int status, String errorMessage) {
                semaphore.release();
                assertNotNull(null);
            }
        });
        lottLuckListService.start();
        semaphore.acquire();
    }

    @Test
    public void checkFifthPokemonName() throws Exception {
        final Semaphore semaphore = new Semaphore(0);
        LottLuckListService lottLuckListService = new LottLuckListService(new ServiceCallback<LottLuckMainModal>() {
            @Override
            public void onSuccess(int status, Response<LottLuckMainModal> response) {
                List<LottLuckItems> lottLuckItems = response.body().getCompanies();
                assertEquals(LottLuckTestData.LOTT_ID_5,lottLuckItems.get(4).getCompanyId());
                semaphore.release();
            }

            @Override
            public void onFailure(int status, String errorMessage) {
                semaphore.release();
                assertNotNull(null);
            }
        });
        lottLuckListService.start();
        semaphore.acquire();
    }
}
