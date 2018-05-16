class Assignment3
{
    int n, count;
    String[][] mpref;
    String[][] wpref;
    String[] men;
    String[] women;
    String[] mpartner;
    String[] wpartner;
    boolean[] mengaged;
    boolean[] wengaged;

    Assignment3(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        n = mp.length;
        count = 0;
        men = m;
        women = w;
        mpref = mp;
        wpref = wp;
        mengaged = new boolean[n];
        wengaged = new boolean[n];
        mpartner = new String[n];
        wpartner = new String[n];
        inversematches();
    }

    /* function to calculate the matches */

    void inversematches()
    {
        while (count < n)
        {
            int i;
            for (i = 0; i < n; i++)
                if (!wengaged[i])
                    break;
            for (int j = 0; j < n && !wengaged[i]; j++)
            {
                int k = menIndexOf(wpref[i][j]);
                if (mpartner[k] == null)
                {
                    mpartner[k] = women[i];
                    wengaged[i] = true;
                    count++;
                }
                else
                {
                    String currentmatch = mpartner[k];
                    if (morePreferenceMen(currentmatch, women[i], k))
                    {
                        mpartner[k] = women[i];
                        wengaged[i] = true;
                        wengaged[womenIndexOf(currentmatch)] = false;
                    }
                }
            }            
        }
        inversecouples();
    }

    /* function to check preferences */

    boolean morePreferenceMen(String curmatch, String newmatch, int i)
    {
        for (int j = 0; j < n; j++)
        {
            if (mpref[i][j].equals(newmatch))
                return true;
            if (mpref[i][j].equals(curmatch))
                return false;
        }
        return false;
    }

    /* get men index */

    int menIndexOf(String str)
    {
        for (int i = 0; i < n; i++)
            if (men[i].equals(str))
                return i;
        return -1;
    }

    /* get women index */

    int womenIndexOf(String str)
    {
        for (int i = 0; i < n; i++)
            if (women[i].equals(str))
                return i;
        return -1;
    }

    /* print couples */
    
    void inversecouples()
    {
        System.out.println("Couples are : ");
        for (int i = 0; i < n; i++)
        {
            System.out.println(men[i] +" - "+ mpartner[i]);
        }
    }
    
    /* main function */

    public static void main(String[] args) 
    {
        System.out.println("Propose and Reject Algorithm\n");
        /* list of men */
        String[] m = {"Zeus", "Yancey", "Xavier", "Wyatt", "Victor"};
        /* list of women */
        String[] w = {"Amy", "Bertha", "Clare", "Diane", "Erika"};
        /* men preference */
        String[][] mp = {{"Bertha", "Diane", "Amy", "Erika", "Clare"},
                         {"Amy", "Diane", "Clare", "Bertha", "Erika"},
                         {"Bertha", "Erika", "Clare", "Diane", "Amy"},
                         {"Diane", "Bertha", "Amy", "Clare", "Erika"},
                         {"Bertha", "Amy", "Diane", "Erika", "Clare"}};
        /* women preference */                      
        String[][] wp = {{"Zeus", "Victor", "Wyatt", "Yancey", "Xavier"}, 
                         {"Xavier", "Wyatt", "Yancey", "Victor", "Zeus"}, 
                         {"Wyatt", "Xavier", "Yancey", "Zeus", "Victor"},
                         {"Victor", "Zeus", "Yancey", "Xavier", "Wyatt"}, 
                         {"Yancey", "Wyatt", "Zeus", "Xavier", "Victor"}};
        Assignment3 ass = new Assignment3(m, w, mp, wp);                        
    }
}