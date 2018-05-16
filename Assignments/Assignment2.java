class Assignment2
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

    Assignment2(String[] m, String[] w, String[][] mp, String[][] wp)
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
        womenmatches();
    }

    /* function to calculate the matches */
    
    void womenmatches()
    {
        while (count < n)
        {
            int i;
            for (i = 0; i < n; i++)
                if (!mengaged[i])
                    break;
            for (int j = 0; j < n && !mengaged[i]; j++)
            {
                int k = womenIndexOf(mpref[i][j]);
                if (wpartner[k] == null)
                {
                    wpartner[k] = men[i];
                    mengaged[i] = true;
                    count++;
                }
                else
                {
                    String currentmatch = wpartner[k];
                    if (morePreferenceWomen(currentmatch, men[i], k))
                    {
                        wpartner[k] = men[i];
                        mengaged[i] = true;
                        mengaged[menIndexOf(currentmatch)] = false;
                    }
                }
            }            
        }
        womencouples();
    }

    /* function to check preferences */
    
    boolean morePreferenceWomen(String curmatch, String newmatch, int i)
    {
        for (int j = 0; j < n; j++)
        {
            if (wpref[i][j].equals(newmatch))
                return true;
            if (wpref[i][j].equals(curmatch))
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
    
    void womencouples()
    {
        System.out.println("Couples are : ");
        for (int i = 0; i < n; i++)
        {
            System.out.println(women[i] +" - "+ wpartner[i]);
        }
        System.out.println();
    }
    
    /* main function */

    public static void main(String[] args) 
    {
        System.out.println("Propose and Reject Algorithm\n");
        /* list of men */
        String[] m = {"Victor", "Wyatt", "Xavier", "Yancey", "Zeus"};
        /* list of women */
        String[] w = {"Amy", "Bertha", "Clare", "Diane", "Erika"};
        /* men preference */
        String[][] mp = {{"Bertha", "Amy", "Diane", "Erika", "Clare"}, 
                         {"Diane", "Bertha", "Amy", "Clare", "Erika"}, 
                         {"Bertha", "Erika", "Clare", "Diane", "Amy"}, 
                         {"Amy", "Diane", "Clare", "Bertha", "Erika"},
                         {"Bertha", "Diane", "Amy", "Erika", "Clare"}};
        /* women preference */                      
        String[][] wp = {{"Zeus", "Victor", "Wyatt", "Yancey", "Xavier"}, 
                         {"Xavier", "Wyatt", "Yancey", "Victor", "Zeus"}, 
                         {"Wyatt", "Xavier", "Yancey", "Zeus", "Victor"},
                         {"Victor", "Zeus", "Yancey", "Xavier", "Wyatt"}, 
                         {"Yancey", "Wyatt", "Zeus", "Xavier", "Victor"}};
        Assignment2 ass = new Assignment2(m, w, mp, wp);                        
    }
}