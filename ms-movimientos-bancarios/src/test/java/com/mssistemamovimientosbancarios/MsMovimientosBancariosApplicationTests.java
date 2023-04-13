package com.mssistemamovimientosbancarios;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.mssistemamovimientosbancarios.MsMovimientosBancariosApplication;

@SpringBootTest
class MsMovimientosBancariosApplicationTests {	
	@Test
    void main() {
        String [] args = new String[1];
        args[0] = new String("app");
        try {
        	MsMovimientosBancariosApplication.main(args);
        }catch (Exception e){
            assertTrue(false);
        }
        assertTrue(true);
    }

}
