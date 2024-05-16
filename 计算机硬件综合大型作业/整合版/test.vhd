LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.ALL;
USE ieee.std_logic_arith.ALL;

ENTITY test IS

    GENERIC (
        sys_clk_fre_value : INTEGER := 50000000;
        div_clk_fre_value : INTEGER := 25000000
    );

    PORT (
        i_sys_clk : IN STD_LOGIC; --ֻ���Ʒ�Ƶ����λ
        i_sys_rst : IN STD_LOGIC; --�����Ƶ��
        i_sys_clear : IN STD_LOGIC; --���Ƽ�ʱ��ʱ�������
        i_sys_stop : IN STD_LOGIC; --stop = 1,��ͣ��ʱ stop = 0, ������ʱ

        i_sys_mode : IN STD_LOGIC_VECTOR(2 DOWNTO 0); --�ı�ģʽ

        i_sys_add_time : IN STD_LOGIC; --����ʱ��
        i_sys_sub_time : IN STD_LOGIC; --����ʱ��

        o_guan_min_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --��ʮλ �������7λ���� 6 downto 0
        o_guan_min_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --���λ
        o_guan_hour_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --��ʮλ
        o_guan_hour_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --�ָ�λ

        -- �������
        t_min_shiwei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --��ʮλ
        t_min_ge_wei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --���λ
        t_hour_shiwei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --��ʮλ
        t_hour_ge_wei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --�ָ�λ

        o_guan_mode_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --ģʽʮλ
        o_guan_mode_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --ģʽ��λ

        t_div_clk : OUT STD_LOGIC; --��Ƶ��ʱ�����

        o_zhengdianbaoshi : OUT STD_LOGIC --���㱨ʱ
    );
END ENTITY test;

ARCHITECTURE behave OF test IS
    SIGNAL r_min_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0); --��ʮλ
    SIGNAL r_min_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0); --���λ
    SIGNAL r_hour_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0); --��ʮλ
    SIGNAL r_hour_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0); --�ָ�λ

    SIGNAL r_guan_min_shiwei : STD_LOGIC_VECTOR(6 DOWNTO 0); --��ʮλ
    SIGNAL r_guan_min_ge_wei : STD_LOGIC_VECTOR(6 DOWNTO 0); --���λ
    SIGNAL r_guan_hour_shiwei : STD_LOGIC_VECTOR(6 DOWNTO 0); --��ʮλ
    SIGNAL r_guan_hour_ge_wei : STD_LOGIC_VECTOR(6 DOWNTO 0); --�ָ�λ

    --��ʼֵ
    SIGNAL init_min_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --��ʮλ
    SIGNAL init_min_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --���λ
    SIGNAL init_hour_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --��ʮλ
    SIGNAL init_hour_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --�ָ�λ

    SIGNAL init_mode_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --��ʮλ
    SIGNAL init_mode_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --�ָ�λ

    SIGNAL r_div_count : STD_LOGIC_VECTOR(31 DOWNTO 0);
    SIGNAL r_div_clk : STD_LOGIC;

    SIGNAL is_ended : STD_LOGIC := '0'; --0δ��� 1�Ѿ����;

BEGIN
    --��Ƶ
    PROCESS (i_sys_clk, i_sys_rst)
    BEGIN
        IF (i_sys_rst = '1') THEN
            r_div_count <= x"00000000";
            r_div_clk <= '0';
        ELSIF (i_sys_clk'event AND i_sys_clk = '1') THEN
            IF (r_div_count = sys_clk_fre_value/div_clk_fre_value/2 - 1) THEN
                r_div_count <= x"00000000";
                r_div_clk <= NOT r_div_clk;
            ELSE
                r_div_count <= r_div_count + 1;
            END IF;
        END IF;
    END PROCESS;

    t_div_clk <= r_div_clk;

    PROCESS (i_sys_clear, r_div_clk, is_ended, i_sys_mode)
    BEGIN
        IF (i_sys_mode = "000") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0001";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0001";
        ELSIF (i_sys_mode = "001") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0010";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0010";
        ELSIF (i_sys_mode = "010") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0011";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0011";
        ELSIF (i_sys_mode = "011") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0100";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0100";
        ELSIF (i_sys_mode = "100") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0101";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0101";
        ELSIF (i_sys_mode = "101") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0000";
            init_hour_ge_wei <= "0001";
            init_hour_shiwei <= "0000";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0110";
        ELSIF (i_sys_mode = "110") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0000";
            init_hour_ge_wei <= "0000";
            init_hour_shiwei <= "0001";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "0111";
        ELSIF (i_sys_mode = "111") THEN
            init_min_ge_wei <= "0000";
            init_min_shiwei <= "0000";
            init_hour_ge_wei <= "0001";
            init_hour_shiwei <= "0001";
            init_mode_shiwei <= "0000";
            init_mode_ge_wei <= "1000";
        END IF;

        


        --ʱ������
        IF (i_sys_clear = '0') THEN
            r_min_ge_wei <= init_min_ge_wei;
            r_min_shiwei <= init_min_shiwei;
            r_hour_ge_wei <= init_hour_ge_wei;
            r_hour_shiwei <= init_hour_shiwei;
        ELSIF (r_div_clk'event AND r_div_clk = '1' AND is_ended = '0') THEN
            --������ͣ ��ͣʱ�ſ��Զ�̬�޸�ʱ��
            IF (i_sys_stop = '1') THEN
                r_min_ge_wei <= r_min_ge_wei;
                r_min_shiwei <= r_min_shiwei;
                r_hour_ge_wei <= r_hour_ge_wei;
                r_hour_shiwei <= r_hour_shiwei;
                IF (i_sys_add_time = '1') THEN
                    r_min_ge_wei <= r_min_ge_wei + 1;
                ELSIF(i_sys_sub_time = '1') THEN
                    r_min_ge_wei <= r_min_ge_wei - 1;   
                END IF ;
            ELSE
            --����ʱ
                IF (r_min_ge_wei = "0000") THEN
                    IF (r_min_shiwei = "0000") THEN
                        IF (r_hour_ge_wei = "0000") THEN
                            IF (r_hour_shiwei = "0000") THEN
                                is_ended <= '1';
                            ELSE
                                r_hour_ge_wei <= "1001";
                                r_hour_shiwei <= r_hour_shiwei - 1;
                            END IF;
                        ELSE
                            r_min_shiwei <= "0111";
                            r_hour_ge_wei <= r_hour_ge_wei - 1;
                        END IF;
                    ELSE
                        r_min_ge_wei <= "1001";
                        r_min_shiwei <= r_min_shiwei - 1;
                    END IF;
                ELSE
                    r_min_ge_wei <= r_min_ge_wei - 1;
                END IF;
            END IF;
        END IF;

        t_min_ge_wei <= r_min_ge_wei;
        t_min_shiwei <= r_min_shiwei;
        t_hour_ge_wei <= r_hour_ge_wei;
        t_hour_shiwei <= r_hour_shiwei;
        
        ---����Ϊ�������ʾ

        ---ģʽ���޸�
        o_guan_mode_shiwei <= "1000000";
        CASE(init_mode_ge_wei) IS
            WHEN"0001" => o_guan_mode_ge_wei <= "1111001";
            WHEN"0010" => o_guan_mode_ge_wei <= "0100100";
            WHEN"0011" => o_guan_mode_ge_wei <= "0110000";
            WHEN"0100" => o_guan_mode_ge_wei <= "0011001";
            WHEN"0101" => o_guan_mode_ge_wei <= "0010010";
            WHEN"0110" => o_guan_mode_ge_wei <= "0000010";
            WHEN"0111" => o_guan_mode_ge_wei <= "1111000";
            WHEN"1000" => o_guan_mode_ge_wei <= "0000000"; 
			WHEN OTHERS => o_guan_mode_ge_wei <= "1111111";
        END CASE ;


        IF (i_sys_clear = '0') THEN
            r_guan_min_ge_wei <= "1000000";
        ELSE
            CASE(r_min_ge_wei) IS
                WHEN"0000" => r_guan_min_ge_wei <= "1000000";
                WHEN"0001" => r_guan_min_ge_wei <= "1111001";
                WHEN"0010" => r_guan_min_ge_wei <= "0100100";
                WHEN"0011" => r_guan_min_ge_wei <= "0110000";
                WHEN"0100" => r_guan_min_ge_wei <= "0011001";
                WHEN"0101" => r_guan_min_ge_wei <= "0010010";
                WHEN"0110" => r_guan_min_ge_wei <= "0000010";
                WHEN"0111" => r_guan_min_ge_wei <= "1111000";
                WHEN"1000" => r_guan_min_ge_wei <= "0000000";
                WHEN"1001" => r_guan_min_ge_wei <= "0010000";
                WHEN OTHERS => r_guan_min_ge_wei <= "1111111";
            END CASE;
        END IF;

        o_guan_min_ge_wei <= r_guan_min_ge_wei;

        IF (i_sys_clear = '0') THEN
            r_guan_min_shiwei <= "1000000";
        ELSE
            CASE(r_min_shiwei) IS
                WHEN"0000" => r_guan_min_shiwei <= "1000000";
                WHEN"0001" => r_guan_min_shiwei <= "1111001";
                WHEN"0010" => r_guan_min_shiwei <= "0100100";
                WHEN"0011" => r_guan_min_shiwei <= "0110000";
                WHEN"0100" => r_guan_min_shiwei <= "0011001";
                WHEN"0101" => r_guan_min_shiwei <= "0010010";
                WHEN OTHERS => r_guan_min_shiwei <= "1111111";
            END CASE;
        END IF;

        o_guan_min_shiwei <= r_guan_min_shiwei;

        IF (i_sys_clear = '0') THEN
            r_guan_hour_ge_wei <= "1000000";
        ELSE
            CASE(r_hour_ge_wei) IS
                WHEN"0000" => r_guan_hour_ge_wei <= "1000000";
                WHEN"0001" => r_guan_hour_ge_wei <= "1111001";
                WHEN"0010" => r_guan_hour_ge_wei <= "0100100";
                WHEN"0011" => r_guan_hour_ge_wei <= "0110000";
                WHEN"0100" => r_guan_hour_ge_wei <= "0011001";
                WHEN"0101" => r_guan_hour_ge_wei <= "0010010";
                WHEN"0110" => r_guan_hour_ge_wei <= "0000010";
                WHEN"0111" => r_guan_hour_ge_wei <= "1111000";
                WHEN"1000" => r_guan_hour_ge_wei <= "0000000";
                WHEN"1001" => r_guan_hour_ge_wei <= "0010000";
                WHEN OTHERS => r_guan_hour_ge_wei <= "1111111";
            END CASE;
        END IF;

        o_guan_hour_ge_wei <= r_guan_hour_ge_wei;

        IF (i_sys_clear = '0') THEN
            r_guan_hour_shiwei <= "1000000";
        ELSE
            CASE(r_hour_shiwei) IS
                WHEN"0000" => r_guan_hour_shiwei <= "1000000";
                WHEN"0001" => r_guan_hour_shiwei <= "1111001";
                WHEN"0010" => r_guan_hour_shiwei <= "0100100";
                WHEN"0011" => r_guan_hour_shiwei <= "0110000";
                WHEN"0100" => r_guan_hour_shiwei <= "0011001";
                WHEN"0101" => r_guan_hour_shiwei <= "0010010";
                WHEN OTHERS => r_guan_hour_shiwei <= "1111111";
            END CASE;
        END IF;

        o_guan_hour_shiwei <= r_guan_hour_shiwei;
    END PROCESS;
END ARCHITECTURE behave;