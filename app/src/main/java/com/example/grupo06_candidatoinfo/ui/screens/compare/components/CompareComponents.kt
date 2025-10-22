package com.example.grupo06_candidatoinfo.ui.screens.compare.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.grupo06_candidatoinfo.model.Candidate
import com.example.grupo06_candidatoinfo.ui.screens.compare.*
import com.example.grupo06_candidatoinfo.ui.theme.*

// ========== HEADER ==========

@Composable
fun ComparisonHeader(candidate1: Candidate, candidate2: Candidate) {
    Surface(modifier = Modifier.fillMaxWidth(), color = cardWhite, shadowElevation = 4.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CandidateCard(candidate1, Modifier.weight(1f))

            Box(modifier = Modifier.width(60.dp).align(Alignment.CenterVertically), contentAlignment = Alignment.Center) {
                Surface(shape = CircleShape, color = primaryPurple, modifier = Modifier.size(50.dp)) {
                    Box(contentAlignment = Alignment.Center) { Text("VS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
                }
            }

            CandidateCard(candidate2, Modifier.weight(1f))
        }
    }
}

@Composable
private fun CandidateCard(candidate: Candidate, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.size(100.dp).border(4.dp, primaryPurple, CircleShape).padding(4.dp)) {
            AsyncImage(model = candidate.photoUrl, contentDescription = candidate.name, modifier = Modifier.fillMaxSize().clip(CircleShape), contentScale = ContentScale.Crop)
        }

        Text(text = candidate.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center, lineHeight = 20.sp)

        Surface(shape = RoundedCornerShape(16.dp), color = lightPurple) {
            Text(text = candidate.party, fontSize = 12.sp, color = darkPurple, fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), textAlign = TextAlign.Center)
        }
    }
}

// ========== TITLES ==========

@Composable
fun SectionTitle(title: String, icon: ImageVector) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 24.dp, bottom = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(imageVector = icon, contentDescription = null, tint = primaryPurple, modifier = Modifier.size(24.dp))
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = darkPurple)
    }
}

// ========== OVERALL SCORE ==========

@Composable
fun OverallScoreComparison(candidate1: Candidate, candidate2: Candidate) {
    ComparisonCard {
        Text("Puntuación General", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = darkPurple, modifier = Modifier.padding(bottom = 20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ScoreCard(score = calculateSimpleScore(candidate1), label = candidate1.name.split(" ").firstOrNull() ?: "", modifier = Modifier.weight(1f))

            ScoreCard(score = calculateSimpleScore(candidate2), label = candidate2.name.split(" ").firstOrNull() ?: "", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        MetricBar("Experiencia", calculateExperience(candidate1), calculateExperience(candidate2))
        Spacer(modifier = Modifier.height(8.dp))
        MetricBar("Educación", calculateEducation(candidate1), calculateEducation(candidate2))
        Spacer(modifier = Modifier.height(8.dp))
        MetricBar("Propuestas", calculateProposals(candidate1), calculateProposals(candidate2))
    }
}

@Composable
private fun ScoreCard(score: Int, label: String, modifier: Modifier = Modifier) {
    val color = when {
        score >= 80 -> accentGreen
        score >= 60 -> accentYellow
        else -> accentRed
    }

    Column(modifier = modifier.background(color.copy(alpha = 0.1f), RoundedCornerShape(12.dp)) .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$score", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = "/100", fontSize = 14.sp, color = neutralGray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun MetricBar(label: String, score1: Int, score2: Int) {
    Column {
        Text(text = label, fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            ProgressBar(score1, score1 >= score2, Modifier.weight(1f))

            Box(modifier = Modifier.width(1.dp).height(20.dp).background(neutralGray.copy(alpha = 0.3f)))

            ProgressBar(score2, score2 > score1, Modifier.weight(1f))
        }
    }
}

@Composable
private fun ProgressBar(score: Int, isWinning: Boolean, modifier: Modifier = Modifier) {
    val progress by animateFloatAsState(targetValue = score / 100f, label = "progress")
    val color = if (isWinning) accentGreen else neutralGray.copy(alpha = 0.5f)

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(modifier = Modifier.weight(1f).height(8.dp).background(neutralGray.copy(alpha = 0.1f), RoundedCornerShape(4.dp))) {
            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(progress).background(color, RoundedCornerShape(4.dp)))
        }
        Text(text = "$score", fontSize = 12.sp, fontWeight = if (isWinning) FontWeight.Bold else FontWeight.Normal, color = if (isWinning) accentGreen else neutralGray, modifier = Modifier.width(30.dp))
    }
}

// ========== COMPARISON CARDS ==========

@Composable
fun ComparisonCard(content: @Composable ColumnScope.() -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = cardWhite), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(20.dp), content = content)
    }
}

@Composable
fun DataRow(label: String, value1: String, value2: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(label, fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(value1, fontSize = 14.sp, color = darkPurple, modifier = Modifier.weight(1f))
            Text(value2, fontSize = 14.sp, color = darkPurple, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun HighlightedDataRow(label: String, value1: String, value2: String, isValue1Better: Boolean, invertColors: Boolean = false) {
    val winColor = if (invertColors) accentRed else accentGreen

    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(label, fontSize = 12.sp, color = neutralGray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.weight(1f).background(if (isValue1Better) winColor.copy(alpha = 0.1f) else Color.Transparent, RoundedCornerShape(8.dp)).padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isValue1Better) {
                        Icon(imageVector = if (invertColors) Icons.Default.Warning else Icons.Default.CheckCircle, contentDescription = null, tint = winColor, modifier = Modifier.size(16.dp).padding(end = 4.dp))
                    }
                    Text(value1, fontSize = 14.sp, fontWeight = if (isValue1Better) FontWeight.Bold else FontWeight.Normal, color = if (isValue1Better) winColor else darkPurple)
                }
            }

            Box(modifier = Modifier.weight(1f).background(if (!isValue1Better) winColor.copy(alpha = 0.1f) else Color.Transparent, RoundedCornerShape(8.dp)).padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!isValue1Better) {
                        Icon(imageVector = if (invertColors) Icons.Default.Warning else Icons.Default.CheckCircle, contentDescription = null, tint = winColor, modifier = Modifier.size(16.dp).padding(end = 4.dp))
                    }
                    Text(value2, fontSize = 14.sp, fontWeight = if (!isValue1Better) FontWeight.Bold else FontWeight.Normal, color = if (!isValue1Better) winColor else darkPurple)
                }
            }
        }
    }
}

// ========== ERROR SCREEN ==========

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.padding(32.dp), colors = CardDefaults.cardColors(containerColor = cardWhite), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("⚠️", fontSize = 64.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Error en la Comparación", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = darkPurple)
                Spacer(modifier = Modifier.height(8.dp))
                Text("No se pudieron cargar los dos candidatos seleccionados.", textAlign = TextAlign.Center, color = neutralGray, fontSize = 14.sp)
            }
        }
    }
}