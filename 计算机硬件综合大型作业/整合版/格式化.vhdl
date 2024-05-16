LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.ALL;
USE ieee.std_logic_arith.ALL;

ENTITY top_level_design IS
    PORT (
        i_sys_clk : IN STD_LOGIC; --只控制分频器复位
        i_sys_start : IN STD_LOGIC; --输入分频器

        i_sys_mode : IN STD_LOGIC_VECTOR(2 DOWNTO 0); --改变模式
        i_sys_stop : IN STD_LOGIC; --stop = 1,暂停计时 stop = 0, 继续计时
        i_sys_add_time : IN STD_LOGIC; --增加时间
        i_sys_sub_time : IN STD_LOGIC; --减少时间

        o_guan_sss_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --秒十位 最终输出7位向量 6 downto 0
        o_guan_sss_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --秒个位
        o_guan_min_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --分十位
        o_guan_min_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --分个位

        o_guan_mode_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --模式十位
        o_guan_mode_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --模式个位

        o_guan_is_ended : OUT STD_LOGIC --是否结束

    );
END ENTITY top_level_design;

ARCHITECTURE behave OF top_level_design IS
    -- 分频器
    COMPONENT frequency_division IS
        GENERIC (
            sys_clk_fre_value : INTEGER := 50000000;
            div_clk_fre_value : INTEGER := 12500000
        );

        PORT (
            i_sys_clk : IN STD_LOGIC; --只控制分频器复位
            i_sys_start : IN STD_LOGIC; --开关
            t_div_clk : OUT STD_LOGIC --分频后时钟输出
        );
    END COMPONENT;
    -- 定时器
    COMPONENT timing_moudle IS
        PORT (
            t_div_clk : IN STD_LOGIC; --分频后时钟输出

            i_sys_start : IN STD_LOGIC; --控制计时器时间的初始化 相当于启动按钮
            i_sys_mode : IN STD_LOGIC_VECTOR(2 DOWNTO 0); --改变模式
            i_sys_stop : IN STD_LOGIC; --stop = 1,暂停计时 stop = 0, 继续计时
            i_sys_add_time : IN STD_LOGIC; --增加时间
            i_sys_sub_time : IN STD_LOGIC; --减少时间

            -- 调试输出
            t_sss_shiwei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --秒十位
            t_sss_ge_wei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --秒个位
            t_min_shiwei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --分十位
            t_min_ge_wei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0); --分个位

            o_mode_shiwei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --模式十位
            o_mode_ge_wei : OUT STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --模式个位

            -- 已经完成的信号
            o_is_ended : OUT STD_LOGIC := '0' --0 未完成 1已经完成;
        );
    END COMPONENT;
    -- 数码管
    COMPONENT digital_tube IS
        PORT (
            -- 输入的4维向量
            i_mode_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --模式十位
            i_mode_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0) := "0000"; --模式个位

            i_sss_shiwei : IN STD_LOGIC_VECTOR(3 DOWNTO 0); --秒十位
            i_sss_ge_wei : IN STD_LOGIC_VECTOR(3 DOWNTO 0); --秒个位
            i_min_shiwei : IN STD_LOGIC_VECTOR(3 DOWNTO 0); --分十位
            i_min_ge_wei : IN STD_LOGIC_VECTOR(3 DOWNTO 0); --分个位

            -- 7段数码管示数 最终输出7位向量 6 downto 0
            o_guan_mode_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --模式十位
            o_guan_mode_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --模式个位

            o_guan_sss_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --秒十位 
            o_guan_sss_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --秒个位
            o_guan_min_shiwei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0); --分十位
            o_guan_min_ge_wei : OUT STD_LOGIC_VECTOR(6 DOWNTO 0) --分个位
        );
    END COMPONENT;

    -- 寄存信号
    SIGNAL w_div_clk : STD_LOGIC; --分频后时钟信号
    SIGNAL w_sys_start : STD_LOGIC; --开关

    SIGNAL w_sys_mode : STD_LOGIC_VECTOR(2 DOWNTO 0); --改变模式
    SIGNAL w_sys_stop : STD_LOGIC; --stop = 1,暂停计时 stop = 0, 继续计时
    SIGNAL w_sys_add_time : STD_LOGIC; --增加时间
    SIGNAL w_sys_sub_time : STD_LOGIC; --减少时间
    -- 下面为输出
    SIGNAL w_sss_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0); --秒十位
    SIGNAL w_sss_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0); --秒个位
    SIGNAL w_min_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0); --分十位
    SIGNAL w_min_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0); --分个位

    SIGNAL w_mode_shiwei : STD_LOGIC_VECTOR(3 DOWNTO 0); --模式十位
    SIGNAL w_mode_ge_wei : STD_LOGIC_VECTOR(3 DOWNTO 0); --模式个位

    -- 端口传递

BEGIN
    U1 : frequency_division PORT MAP(
        i_sys_clk => i_sys_clk,
        i_sys_start => w_sys_start,
        t_div_clk => w_div_clk
    );

    U2 : timing_moudle PORT MAP(
        t_div_clk => w_div_clk,
        i_sys_start => w_sys_start,
        i_sys_mode => w_sys_mode,
        i_sys_stop => w_sys_stop,
        i_sys_add_time => w_sys_add_time,
        i_sys_sub_time => w_sys_sub_time,
        -- 上面为输入 下面为输出
        t_sss_shiwei => w_sss_shiwei, --秒十位
        t_sss_ge_wei => w_sss_ge_wei, --秒个位
        t_min_shiwei => w_min_shiwei, --分十位
        t_min_ge_wei => w_min_ge_wei, --分个位

        o_mode_shiwei => w_mode_shiwei, --模式十位
        o_mode_ge_wei => w_mode_ge_wei, --模式个位

        -- 已经完成的信号
        o_is_ended => o_guan_is_ended--0 未完成 1已经完成;
    );

    U3 : digital_tube PORT MAP(
        i_sss_shiwei => w_sss_shiwei,
        i_sss_ge_wei => w_sss_ge_wei,
        i_min_shiwei => w_min_shiwei,
        i_min_ge_wei => w_min_ge_wei,

        o_guan_sss_shiwei => o_guan_sss_shiwei, --秒十位
        o_guan_sss_ge_wei => o_guan_sss_ge_wei, --秒个位
        o_guan_min_shiwei => o_guan_min_shiwei, --分十位
        o_guan_min_ge_wei => o_guan_min_ge_wei, --分个位

        o_guan_mode_shiwei => o_guan_mode_shiwei, --模式十位
        o_guan_mode_ge_wei => o_guan_mode_ge_wei --模式个位
    );
END ARCHITECTURE behave;