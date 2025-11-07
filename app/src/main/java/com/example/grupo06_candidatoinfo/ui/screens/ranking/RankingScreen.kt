package com.example.grupo06_candidatoinfo.ui.screens.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.viewmodel.VoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingScreen(navController: NavController, voteViewModel: VoteViewModel = viewModel()) {
    val rankedCandidates = voteViewModel.getRankedCandidates().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ranking de Candidatos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3C3472))
            )
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Top3Candidates(rankedCandidates.value)
            }
            item {
                RankingList(rankedCandidates.value)
            }
        }
    }
}

@Composable
fun Top3Candidates(candidates: List<Pair<Candidate, Int>>) {
    val top3 = candidates.take(3)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.EmojiEvents, contentDescription = "Top 3", tint = Color(0xFFFFD700))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Top 3 Candidatos", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (top3.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    // 2nd Place
                    if (top3.size > 1) {
                        PodiumItem(candidate = top3[1].first, rank = 2, votes = top3[1].second, color = Color(0xFFE8E5F2))
                    }
                    // 1st Place
                    PodiumItem(candidate = top3[0].first, rank = 1, votes = top3[0].second, color = Color(0xFFFFD700).copy(alpha = 0.5f))
                    // 3rd Place
                    if (top3.size > 2) {
                        PodiumItem(candidate = top3[2].first, rank = 3, votes = top3[2].second, color = Color(0xFFE5A76F).copy(alpha = 0.5f))
                    }
                }
            } else {
                Text("Aún no hay votos registrados.", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun PodiumItem(candidate: Candidate, rank: Int, votes: Int, color: Color) {
    val size = if (rank == 1) 90.dp else 70.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(candidate.name.split(" ").map { it.first() }.joinToString(""), fontSize = if (rank == 1) 32.sp else 24.sp, fontWeight = FontWeight.Bold)
        }
        Text(candidate.name.split(" ").first(), fontWeight = FontWeight.SemiBold)
        Text(candidate.name.split(" ").last(), color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = color)
        ) {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${rank}º", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("$votes votos", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun RankingList(candidates: List<Pair<Candidate, Int>>) {
    val restOfCandidates = candidates.drop(3)
    if (restOfCandidates.isNotEmpty()){
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = "Ranking Completo", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ranking Completo", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    restOfCandidates.forEachIndexed { index, (candidate, votes) ->
                        RankingListItem(rank = index + 4, candidate = candidate, votes = votes)
                        if (index < restOfCandidates.lastIndex) {
                            Divider(color = Color.LightGray.copy(alpha = 0.5f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RankingListItem(rank: Int, candidate: Candidate, votes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8E5F2)),
            contentAlignment = Alignment.Center
        ) {
            Text(" #$rank", fontWeight = FontWeight.Bold, color = Color(0xFF3C3472))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(candidate.name, fontWeight = FontWeight.SemiBold)
            Text(candidate.party, color = Color.Gray, fontSize = 12.sp)
        }
        Text("$votes votos", fontWeight = FontWeight.Bold, color = Color(0xFF3C3472))
    }
}
