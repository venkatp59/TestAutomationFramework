package com.enquero.PayLoads;

public class PayLoad {
    public   String getPayloadLatestVersion()
    {
        String s ="{\n" +
                "    \"_links\": {\n" +
                "        \"self\": {\n" +
                "            \"href\": \"/rest/2/products/key/jira-software/versions/name/8.8.1\"\n" +
                "        },\n" +
                "        \"artifact\": {\n" +
                "            \"href\": \"/rest/2/assets/artifact%2Fcc77fa10-2283-4228-94c3-6a6582ddb6ee%2Fjira-software-application-8.8.1.obr\"\n" +
                "        },\n" +
                "        \"releaseNotes\": {\n" +
                "            \"href\": \"https://confluence.atlassian.com/display/JIRASOFTWARE/JIRA%20Software%208.8.x%20release%20notes#JIRASoftware8.8.xreleasenotes-8-8-1\",\n" +
                "            \"type\": \"text/html\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"_embedded\": {\n" +
                "        \"artifact\": {\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"/rest/2/assets/artifact%2Fcc77fa10-2283-4228-94c3-6a6582ddb6ee%2Fjira-software-application-8.8.1.obr\"\n" +
                "                },\n" +
                "                \"binary\": {\n" +
                "                    \"href\": \"https://marketplace.atlassian.com/download/apps/1213607/version/808001\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"name\": \"8.8.1\",\n" +
                "    \"buildNumber\": 808001,\n" +
                "    \"paymentModel\": \"atlassian\",\n" +
                "    \"releaseDate\": \"2020-04-22\",\n" +
                "    \"compatibilities\": [\n" +
                "        {\n" +
                "            \"application\": \"jira\",\n" +
                "            \"hosting\": {\n" +
                "                \"server\": {\n" +
                "                    \"min\": {\n" +
                "                        \"build\": 808001,\n" +
                "                        \"version\": \"8.8.1\"\n" +
                "                    },\n" +
                "                    \"max\": {\n" +
                "                        \"build\": 808001,\n" +
                "                        \"version\": \"8.8.1\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return s;
    }

}
