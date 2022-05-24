package gbchat.server.test;

import gbchat.server.hw3_6;
import org.junit.*;

import java.util.Arrays;
import java.util.Collection;


public class hw3_6Test{
    public hw3_6 hw3_6 = new hw3_6();

    @Test
        public  Collection<Object[]> data() {
            return Arrays.asList(new int[][][]{
                    {{1, 5, 4, 6}, {6}},
                    {{4, 2, 3, 5}, {2, 3, 5}},
                    {{4, 4, 0, 1}, {0, 1}},
                    {{5, 5, 5, 4}, {}},
                    {{4, 7, 5, 4}, {}},
                    {{5, 5, 4, 5,}, {5} },
            });
        }

        private int x[];
        private int result[];

        public hw3_6Test(int x[], int result[]) {
            this.x = x;
            this.result = result;
        }

        private static hw3_6 method;


        @Before
        public void init() {
            System.out.println("init Method");
            method = new hw3_6();
        }

        @Test
        public void massTestAdd() {
            Assert.assertArrayEquals(result, hw3_6.case1(x));
        }

    }