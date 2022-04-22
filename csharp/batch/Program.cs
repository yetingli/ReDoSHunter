using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace RedosCheck
{
    class Program
    {
        static void Main(string[] args)
        {

            string path = "D://csharp_only_check";
            var files = Directory.GetFiles(path, "*.txt");

           string file = "D://csharp_only_check//10_only_check_s_js_11111_0_2021_12_14_17_27_45.txt";
           Console.WriteLine(file); //输出的路径+名称
           Readjson(file);
           
           

            /*  string pattern = "^([\\w]+)+b";
              string str = "";
              for(int i = 0; i < 10000; i++)
              {
                  str = str + "aaa";
                  DateTime beforDT = System.DateTime.Now;
                  try
                  {
                      bool b = Regex.IsMatch(str + "!", pattern, RegexOptions.None, TimeSpan.FromMilliseconds(1000));
                      DateTime afterDT = System.DateTime.Now;
                      TimeSpan ts = afterDT.Subtract(beforDT);
                      Console.WriteLine(b);
                      Console.WriteLine("DateTime总共花费{0}ms.", ts.TotalMilliseconds);
                      Console.WriteLine(str.Length);

                  }
                  catch (RegexMatchTimeoutException e)
                  {
                      Console.WriteLine(e.ToString());

                  }
              }
              */
            Console.Read();

        }


        public static void Readjson(string jsonfile)
        {

            using (System.IO.StreamReader file = System.IO.File.OpenText(jsonfile))
            {
                using (JsonTextReader reader = new JsonTextReader(file))
                {
                    JArray jArray = (JArray)JToken.ReadFrom(reader);
                    JArray list = new JArray();
                    foreach (var jsonitem in jArray)
                    {
                        JObject job = (JObject)jsonitem;
                        string id = job["id"].ToString();
                        string regex = job["regex"].ToString();
                        JArray array = (JArray)job["attackArrayList"];
                   
                        string type = "";
                        string patternType = "";
                        string redos =" false";
                        int i = 0;
                 
                        foreach (var a in array)
                        {
                            i++;
                            Console.WriteLine(id +":" + i);
                            if (i > 1000) {
                                break;
                            }
                            JObject attack = (JObject)a;

                            string prefix = attack["prefix"].ToString();
                            string infix = attack["infix"].ToString();
                            string suffix = attack["suffix"].ToString();
                            type = attack["type"].ToString();
                            patternType = attack["patternType"].ToString();
                            if (type == "EXPONENT")
                            {
                                redos = GetAttack(regex, prefix, infix, suffix, 50);
                            }
                            else if (type == "POLYNOMIAL")
                            {
                                redos = GetAttack(regex, prefix, infix, suffix, 5000);
                            }
                            else {

                                redos = GetAttack(regex, prefix, infix, suffix, 50);
                                type = "EXPONENT";
                                if (redos == "falae")
                                {
                                    redos = GetAttack(regex, prefix, infix, suffix, 5000);
                                    type = "POLYNOMIAL";
                                }
                            }
                            Console.WriteLine(redos);
                            if (redos == "true" || redos == "error") {

                                break;
                            }
                        }

                        if (redos == "error") {
                            redos = "false";
                        }
                    
                        JObject obj = new JObject();
                        obj["id"] = id;
                        obj["redos"] = redos;
                        obj["type"] = type;
                        obj["patternType"] = patternType;
                        list.Add(obj);
                    }

                    string convertString = Convert.ToString(list);//将json装换为string
                    File.WriteAllText(jsonfile.Replace(".txt",".result.json"), convertString, System.Text.Encoding.UTF8);//将内容写进jon文件中
                }
            }
        }


        public static string GetAttack(string regex, string prefix, string infix, string suffix, int time) {

            string str = prefix;
            for (int i = 0; i < time; i++)
            {
                str = str + infix;
            }
            str = str + suffix;
            try
            {
                bool b = Regex.IsMatch(str, regex, RegexOptions.None, TimeSpan.FromMilliseconds(1000));
            }
            catch (RegexMatchTimeoutException e)
            {
                Console.WriteLine(e.ToString());
                return "true";

            }
            catch (Exception) {
                Console.WriteLine("error");
                return "error";
            }
            return "false";

        }
       
    }
}
